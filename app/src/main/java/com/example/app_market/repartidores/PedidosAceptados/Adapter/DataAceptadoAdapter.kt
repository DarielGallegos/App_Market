package com.example.app_market.repartidores.PedidosAceptados.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.R
import com.example.app_market.repartidores.PedidosAceptados.DataAceptados

class DataAceptadoAdapter(private val ListAceptado: List<DataAceptados>, private val OnClickListener:(DataAceptados)->Unit) :
    RecyclerView.Adapter<DataAceptadoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAceptadoViewHolder {
       val LayoutInflater= LayoutInflater.from(parent.context)
        return DataAceptadoViewHolder(LayoutInflater.inflate(R.layout.item_dataaceptado,parent,false))
    }

    override fun onBindViewHolder(holder: DataAceptadoViewHolder, position: Int) {
        val item=ListAceptado[position]
        holder.render(item, OnClickListener)
    }

    override fun getItemCount(): Int = ListAceptado.size
}