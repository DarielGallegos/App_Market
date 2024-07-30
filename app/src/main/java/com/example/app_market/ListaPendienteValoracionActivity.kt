package com.example.app_market

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import client.Client
import client.services.PedidoService
import com.example.app_market.adapters.ListPendientesAdapter
import com.example.app_market.databinding.ActivityCatalogoProductosBinding
import com.example.app_market.databinding.ActivityListaPendienteValoracionBinding
import com.google.gson.Gson
import model.common.ApiResponseBody
import model.common.PedidoValorar
import model.common.Producto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaPendienteValoracionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaPendienteValoracionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityListaPendienteValoracionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pedidos: List<PedidoValorar>? = null
        var adapter: ListPendientesAdapter? = null


        val rec_view = binding.listaPendientes
        rec_view.layoutManager = LinearLayoutManager(this)

        binding.Inicio.setOnClickListener{

            val intent = Intent(this, DashboardClient::class.java)
            startActivity(intent)
        }

        val apiService = Client.ClientRetrofit.getService(PedidoService::class.java) as PedidoService
        val call = apiService.getPedidosSValorar( IdCliente = 22 )

        call.enqueue(object : Callback<ApiResponseBody> {

            override
            fun onResponse(call: Call<ApiResponseBody>, response: Response<ApiResponseBody>) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    pedidos = gson.fromJson(gson.toJson(response.body()?.data?.content), Array<PedidoValorar>::class.java).toList()

                    binding.listaPendientes.adapter = ListPendientesAdapter(this@ListaPendienteValoracionActivity, pedidos ?: emptyList())

                } else {
                    Toast.makeText(
                        this@ListaPendienteValoracionActivity,
                        "Error: " + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override
            fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                Toast.makeText(this@ListaPendienteValoracionActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }
}