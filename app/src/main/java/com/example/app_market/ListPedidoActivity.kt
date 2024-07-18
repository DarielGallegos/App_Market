package com.example.app_market
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import client.Client
import client.services.PedidoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.app_market.Adapter.adapter_pedidos
import com.example.app_market.databinding.ActivityListPedidosBinding
import com.google.gson.Gson
import model.common.ApiResponseBody
import model.common.Pedidos

class ListPedidoActivity: AppCompatActivity() {

        private lateinit var binding: ActivityListPedidosBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityListPedidosBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val rec_view = binding.viewpedidos
            rec_view.layoutManager = GridLayoutManager(this, 2)

            val apiService = Client.ClientRetrofit.getService(PedidoService::class.java) as PedidoService
            val call = apiService.listarPedidos()

            call.enqueue(object : Callback<ApiResponseBody>{

                override
                fun onResponse(call: Call <ApiResponseBody>, response: Response<ApiResponseBody>) {
                    if (response.isSuccessful()) {

                        val productos: List<Pedidos>? = Gson().fromJson(Gson().toJson(response.body()?.data?.content),Array<Pedidos>::class.java).toList()
                        rec_view.adapter = adapter_pedidos(
                            productos
                        )

                        //  adapter = ProductsAdapter(product)
                        // recyclerView.setAdapter(adapter)
                    } else {
                        Toast.makeText(
                            this@ListPedidoActivity,
                            "Error: " + response.code(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override
                fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                    Toast.makeText(this@ListPedidoActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })



        }
    }
