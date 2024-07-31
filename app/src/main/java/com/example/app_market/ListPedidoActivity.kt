package com.example.app_market

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import client.Client
import client.services.PedidoDetallesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.app_market.Adapter.adapter_pedidos
import com.example.app_market.databinding.ActivityListPedidosBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.common.ApiResponseBody
import model.common.Pedidos
import storage.StoragePreferences

class ListPedidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListPedidosBinding
    private var preference = StoragePreferences.getInstance(this)
    private lateinit var rec_view: RecyclerView
    private lateinit var recView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rec_view = binding.viewpedidos
        recView  = binding.viewpedidos
        recView.layoutManager = LinearLayoutManager(this)
        val imageViewBack: ImageView = findViewById(R.id.imageViewBack)
        imageViewBack.setOnClickListener {
            finish()}

        //corrutina va al hilo secundario ,luego si se ejecuta bien pasa al principal
        lifecycleScope.launch(Dispatchers.IO) {
            preference.getCredentiales().collect() {
                withContext(Dispatchers.Main) {
                    if(it.id != null){
                       getData(it.id)
                        getData(1)
                    }else{
                        getData(0)
                    }
                }
            }
        }
    }

    private fun getData(id: Int){
        val apiService =
            Client.ClientRetrofit.getService(PedidoDetallesService::class.java) as PedidoDetallesService
        val call = apiService.listarPedidos(id)

        call.enqueue(object : Callback<ApiResponseBody> {

            override
            fun onResponse(call: Call<ApiResponseBody>, response: Response<ApiResponseBody>) {
                if (response.isSuccessful()) {
                    val pedidos: List<Pedidos>? = Gson().fromJson(
                        Gson().toJson(response.body()?.data?.content),
                        Array<Pedidos>::class.java
                    ).toList()
                    rec_view.adapter = adapter_pedidos(pedidos) { pedido ->
                        // Crear el Intent para iniciar DetallesPedidoActivity
                        val intent = Intent(this@ListPedidoActivity, PedidoDetallesActivity::class.java)
                        intent.putExtra("numeroPedido", pedido.pedido_numero)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this@ListPedidoActivity, "Error: " + response.code(), Toast.LENGTH_SHORT).show()
                }
            }

            override
            fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                Toast.makeText(this@ListPedidoActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })


    }
}
