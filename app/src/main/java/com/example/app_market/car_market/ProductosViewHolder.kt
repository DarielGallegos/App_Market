package com.example.app_market.car_market

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.ItemProductosBinding
import model.common.Producto

class ProductosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemProductosBinding.bind(view)

    fun render(productoModel: Producto, onClickListener: (Producto) -> Unit, onDeleteListener: (Producto) -> Unit) {
        binding.tvProductoName.text = productoModel.nombre
        binding.tvProductoPrice.text = productoModel.precio.toString()
        binding.tvProductoCant.text = productoModel.cantidad.toString()

        //Glide.with(binding.ivProducto.context).load(productoModel.foto).into(binding.ivProducto)

        itemView.setOnClickListener {
            onClickListener(productoModel)
        }

        binding.btnEliminar.setOnClickListener {
            onDeleteListener(productoModel)
        }
    }
}