package com.example.app_market.repartidores.ListaProductos.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.R
import model.dto.REQUEST.ProductosDetalle

class DataProductoAdapter (private val ListProducto: List<ProductosDetalle>, private val OnClickListener:(ProductosDetalle)->Unit): RecyclerView.Adapter<DataProductoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataProductoViewHolder {
        val LayaoutInflater = LayoutInflater.from(parent.context)
        return DataProductoViewHolder(LayaoutInflater.inflate(R.layout.item_datalistaproductos, parent, false))
    }


    override fun onBindViewHolder(holder: DataProductoViewHolder, position: Int) {
        val item = ListProducto[position]
        holder.render(item, OnClickListener)
    }

    override fun getItemCount(): Int = ListProducto.size
}