package com.example.app_market

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import client.Client
import client.services.PedidoDetallesService
import com.example.app_market.Adapter.productor_adapter
import com.example.app_market.car_market.DetallesProductosFinancieros
import com.example.app_market.databinding.ActivityDetallesPedidoBinding
import com.google.gson.Gson
import model.common.ApiResponseBody
import model.common.PedidosDetalles
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PedidoDetallesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesPedidoBinding
    private lateinit var usuario: String
    private lateinit var ubicacion: String
    private var idUsuario = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesPedidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numeroPedido = intent.getIntExtra("numeroPedido", 0)
        val imageViewBack: ImageView = findViewById(R.id.imageViewBack)
        imageViewBack.setOnClickListener {
            finish()}


        if (numeroPedido != 0) {
            obtenerDetallesPedido(numeroPedido)
        } else {
            Toast.makeText(this, "No se pudieron obtener los detalles del pedido", Toast.LENGTH_SHORT).show()
        }

        binding.btnMapa.setOnClickListener{
            if(idUsuario != 0 && idUsuario != 1){
                val intent = Intent(this, TrackingActivity::class.java)
                intent.putExtra("idUsuario", idUsuario)
                intent.putExtra("usuario", usuario)
                intent.putExtra("ubicacion", ubicacion)
                intent.putExtra("numeroPedido", numeroPedido)
                startActivity(intent)
            }
        }

    }

    private fun obtenerDetallesPedido(pedidoNumero: Int) {
        val apiService = Client.ClientRetrofit.getService(PedidoDetallesService::class.java) as PedidoDetallesService
        val call = apiService.obtenerDetallesPedido(pedidoNumero)

        call.enqueue(object : Callback<ApiResponseBody> {
            override fun onResponse(call: Call<ApiResponseBody>, response: Response<ApiResponseBody>) {
                if (response.isSuccessful) {
                    val detallesJson = Gson().toJson(response.body()?.data?.content)
                    val detalles = Gson().fromJson(detallesJson, Array<PedidosDetalles>::class.java).toList()
                    mostrarDetallesPedido(detalles[0])
                } else {
                    Log.e("PedidoDetallesActivity", "Error en la respuesta: ${response.code()}")
                    Toast.makeText(this@PedidoDetallesActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                Log.e("PedidoDetallesActivity", "Error: ${t.message}")
                Toast.makeText(this@PedidoDetallesActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun mostrarDetallesPedido(pedido: PedidosDetalles) {
        binding.txtNumPedido.text = pedido.numeroPedido.toString()
        binding.txtEstado.text = pedido.estado
        binding.txtDireccion.text = pedido.destino
        binding.txtUsuario.text = pedido.usuario
        binding.txtCliente.text = pedido.cliente
        binding.txtTotal.text = pedido.total.toString()

        idUsuario = pedido.idUsuario
        usuario = pedido.usuario
        ubicacion = pedido.destino

        val recyclerView = binding.recyclerProductos
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productor_adapter(pedido.productos)
    }
}
/*

        val adapter = productor_adapter(pedido.productos)
        binding.recyclerProductos.layoutManager = LinearLayoutManager(this@PedidoDetallesActivity)
        binding.recyclerProductos.adapter = adapter*/

      //  val recyclerView: RecyclerView = binding.recyclerProductos
       // recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = productor_adapter(pedido.productos)

   // }
//}


                        // Mostrar los detalles del pedido en la UI
                       /* //binding.txtNumPedido.text = it.pedido_numero.toString()
                        binding.txtNumPedido.text = it.pedidoNumero.toString()
                        binding.txtPrecio.text = it.precio.toString()
                        binding.txtCantidad.text = it.cantidad.toString()
                        binding.txtMonto.text = it.monto.toString()
                        binding.txtProducto.text = it.nombreProducto
                        binding.ImgPedido.setImageBitmap(Converters().base64ToBitmap(it.imagen))
                       // binding.txtNumPedido.text = "12345"
                        //binding.txtPrecio.text = "99.99"
                        //binding.txtCantidad.text = "3"
                        //binding.txtMonto.text = "299.97"
                        //binding.txtProducto.text = "Producto de prueba"
                        //binding.ImgPedido.setImageResource(R.drawable.logo)

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
    }}*/

