package com.example.app_market.car_market

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.ItemProductosBinding
import model.common.CarMarketProducto

class ProductosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemProductosBinding.bind(view)

    fun render(carMarketProductoModel: CarMarketProducto, onClickListener: (CarMarketProducto) -> Unit, onDeleteListener: (CarMarketProducto) -> Unit) {
        binding.tvProductoName.text = carMarketProductoModel.nombre
        binding.tvProductoPrice.text = carMarketProductoModel.precio.toString()
        binding.tvProductoCant.text = carMarketProductoModel.cantidad.toString()

        //Glide.with(binding.ivProducto.context).load(productoModel.foto).into(binding.ivProducto)

        itemView.setOnClickListener {
            onClickListener(carMarketProductoModel)
        }

        binding.btnEliminar.setOnClickListener {
            onDeleteListener(carMarketProductoModel)
        }
    }
}