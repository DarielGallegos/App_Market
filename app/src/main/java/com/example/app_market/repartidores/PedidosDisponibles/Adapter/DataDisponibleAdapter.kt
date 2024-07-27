package com.example.app_market.repartidores.PedidosDisponibles.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.R
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned

class DataDisponibleAdapter(private val ListDisponible: List<CabeceraPedidosUnsignedAndSigned>, private val OnClickListener:(CabeceraPedidosUnsignedAndSigned)->Unit) : RecyclerView.Adapter<DataDisponibleViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataDisponibleViewHolder {
        val LayoutInflater=LayoutInflater.from(parent.context)
        return DataDisponibleViewHolder(LayoutInflater.inflate(R.layout.item_datadisponible,parent,false))
    }
    override fun onBindViewHolder(holder: DataDisponibleViewHolder, position: Int) {
        val item=ListDisponible[position]
        holder.render(item, OnClickListener)
    }
    override fun getItemCount(): Int = ListDisponible.size
}