package com.example.app_market.repartidores.ListaProductos.Adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.ItemDatalistaproductosBinding

import model.dto.REQUEST.ProductosDetalle
import utils.Converters

class DataProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemDatalistaproductosBinding.bind(view)

    fun render(e: ProductosDetalle, onClickListener: (ProductosDetalle) -> Unit){
        binding.NProducto.text= e.nombreProducto
        binding.CProducto.text = e.cantidad.toString()
        binding.photo.setImageBitmap(Converters().base64ToBitmap(e.imagen))
        itemView.setOnClickListener { onClickListener(e) }
    }
}