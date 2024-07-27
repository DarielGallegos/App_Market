package com.example.app_market

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import client.Client
import client.services.PedidoService
import com.example.app_market.databinding.ActivityDetallesPedidoBinding
import com.google.gson.Gson
import model.common.ApiResponseBody
import model.common.Pedidos
import model.common.PedidosDetalles
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.Converters

class PedidoDetallesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesPedidoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesPedidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el pedido pasado a través del Intent
        val numeroPedido = intent.getIntExtra("numeroPedido", 0)

        // Hacer la petición a la API para obtener los detalles del pedido
        if (numeroPedido != 0) {
           // mostrarDetallesPedido()
            obtenerDetallesPedido(numeroPedido)
        } else {
            Toast.makeText(
                this,
                "No se pudieron obtener los detalles del pedido",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

   // private fun mostrarDetallesPedido(pedido: Pedidos) {
   //     binding.txtNumPedido.text = pedido.pedido_numero.toString()
    //}


    private fun obtenerDetallesPedido(pedidoNumero: Int) {

        val apiService =
            Client.ClientRetrofit.getService(PedidoService::class.java) as PedidoService
        val call = apiService.obtenerDetallesPedido(pedidoNumero)

        call.enqueue(object : Callback<ApiResponseBody> {
            override fun onResponse(
                call: Call<ApiResponseBody>,
                response: Response<ApiResponseBody>
            ) {
                if (response.isSuccessful) {
                    val detallesJson = Gson().toJson(response.body()?.data)
                    val detalles = Gson().fromJson(detallesJson, PedidosDetalles::class.java)
                    detalles?.let {
                        Log.d("PedidoDetallesActivity", "Detalles recibidos: $detalles")

                        // Mostrar los detalles del pedido en la UI
                        //binding.txtNumPedido.text = it.pedido_numero.toString()
                        /*binding.txtNumPedido.text = it.pedidoNumero.toString()
                        binding.txtPrecio.text = it.precio.toString()
                        binding.txtCantidad.text = it.cantidad.toString()
                        binding.txtMonto.text = it.monto.toString()
                        binding.txtProducto.text = it.nombreProducto
                        binding.ImgPedido.setImageBitmap(Converters().base64ToBitmap(it.imagen))*/
                        binding.txtNumPedido.text = "12345"
                        binding.txtPrecio.text = "99.99"
                        binding.txtCantidad.text = "3"
                        binding.txtMonto.text = "299.97"
                        binding.txtProducto.text = "Producto de prueba"
                        binding.ImgPedido.setImageResource(R.drawable.logo)

                    } ?: run {
                        Log.e("PedidoDetallesActivity", "Detalles del pedido son nulos")
                        Toast.makeText(
                            this@PedidoDetallesActivity,
                            "Detalles del pedido son nulos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Log.e("PedidoDetallesActivity", "Error en la respuesta: ${response.code()}")
                    Toast.makeText(
                        this@PedidoDetallesActivity,
                        "Error: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                Toast.makeText(
                    this@PedidoDetallesActivity,
                    "Error: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }}

