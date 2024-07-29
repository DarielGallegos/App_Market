package com.example.app_market.Administracion.Entregados.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.Administracion.Entregados.Data.DataEntregados
import com.example.app_market.R

class DataEntregadosAdapter(private val ListEntregados: List<DataEntregados>, private val OnClickListener:(DataEntregados)->Unit): RecyclerView.Adapter<DataEntregradosViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataEntregradosViewHolder {
      val LayoutInflater= LayoutInflater.from(parent.context)
      return DataEntregradosViewHolder(LayoutInflater.inflate(R.layout.item_dataentregados,parent,false))
    }

    override fun onBindViewHolder(holder: DataEntregradosViewHolder, position: Int) {
      val item=ListEntregados[position]
      holder.render(item,OnClickListener)
    }

    override fun getItemCount(): Int = ListEntregados.size
}