package com.example.app_market.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_market.databinding.ActivityDetallePedidosBinding
import model.common.PedidosDetalles
import com.google.gson.Gson
import utils.Converters

class DetallesPedidoActivity : AppCompatActivity()  {

        private lateinit var binding: ActivityDetallePedidosBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityDetallePedidosBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Obtener el pedido pasado a través del Intent
            val pedidoJson = intent.getStringExtra("pedidoDetalles")
            val pedido = Gson().fromJson(pedidoJson, PedidosDetalles::class.java)

            // Mostrar los detalles del pedido en la UI
            binding.txtNumPedido.setText(pedido.NombreProducto)
            binding.txtPrecio.setText(pedido.Precio.toString())
            binding.txtCantidad.setText(pedido.Cantidad.toString())
            binding.txtMonto.setText(pedido.Monto.toString())
            binding.txtProducto.setText(pedido.NombreProducto)
            binding.Pedido.setImageBitmap(Converters().base64ToBitmap(pedido.Imagen))
            // Glide.with(this).load(pedido.Imagen).into(binding.Pedido)
        }
    }
