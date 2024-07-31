package com.example.app_market.car_market

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.ItemProductosBinding
import model.common.Producto
import utils.Converters

class ProductosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemProductosBinding.bind(view)

    fun render(producto: Producto, onClickListener: (Producto) -> Unit, onDeleteListener: (Producto) -> Unit) {
        binding.tvProductoName.text = producto.producto
        binding.tvProductoPrice.text = producto.precio.toString()
        binding.tvProductoCant.text = producto.cantidad.toString()

        val bitmap = Converters().base64ToBitmap(producto.foto)
        binding.ivProducto.setImageBitmap(bitmap)

        itemView.setOnClickListener {
            onClickListener(producto)
        }

        binding.btnEliminar.setOnClickListener {
            onDeleteListener(producto)
        }
    }
}