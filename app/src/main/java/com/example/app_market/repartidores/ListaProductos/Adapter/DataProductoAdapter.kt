package com.example.app_market.repartidores.ListaProductos.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.R
import com.example.app_market.repartidores.ListaProductos.DataProductos

class DataProductoAdapter (private val ListProducto: List<DataProductos>, private val OnClickListener:(DataProductos)->Unit): RecyclerView.Adapter<DataProductoViewHolder>() {
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