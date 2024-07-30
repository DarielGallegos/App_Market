package com.example.app_market

import android.content.Intent
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import client.Client
import client.services.PedidoService
import com.example.app_market.adapters.CatProductosAdapter
import com.example.app_market.databinding.ActivityValoracionPedidoBinding
import com.google.gson.Gson
import model.common.ApiResponseBody
import model.common.PedidoValorar
import model.common.Producto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.Converters

class ValoracionPedidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityValoracionPedidoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()


        val extras = intent.extras
        val pedidoNumero = extras!!.getInt("pedidoNumero")


        binding = ActivityValoracionPedidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            binding.puntuacion.text = rating.toString()
        }

        binding.btnahorano.setOnClickListener{
            val intent = Intent(this, ListaPendienteValoracionActivity::class.java)
            startActivity(intent)
        }

        binding.btnenviar.setOnClickListener{

            val apiService = Client.ClientRetrofit.getService(PedidoService::class.java) as PedidoService
            val call = apiService.pedidoFinalizado( NumPedido = pedidoNumero )

            call.enqueue(object : Callback<ApiResponseBody> {

                override
                fun onResponse(call: Call<ApiResponseBody>, response: Response<ApiResponseBody>) {
                    if (response.isSuccessful) {

                        val msg = response.body()?.data?.msg?.joinToString() ?: "Pedido finalizado con éxito"
                        Toast.makeText(this@ValoracionPedidoActivity, msg, Toast.LENGTH_SHORT).show()


                    } else {
                        Toast.makeText(
                            this@ValoracionPedidoActivity,
                            "Error: " + response.code(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override
                fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                    Toast.makeText(this@ValoracionPedidoActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })


            val intent = Intent(this, ListaPendienteValoracionActivity::class.java)
            startActivity(intent)
        }

    }
}


