package com.example.app_market.repartidores.ListaProductos.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.ItemDatalistaproductosBinding

import com.example.app_market.repartidores.ListaProductos.DataProductos

class DataProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemDatalistaproductosBinding.bind(view)

    fun render(DataProductosModel: DataProductos, onClickListener: (DataProductos) -> Unit){
        binding.NProducto.text= DataProductosModel.producto
        binding.CProducto.text = DataProductosModel.cantidad
        itemView.setOnClickListener { onClickListener(DataProductosModel) }

    }
}