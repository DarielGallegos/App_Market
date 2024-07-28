package com.example.app_market.Administracion.Pendientes.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.Administracion.Pendientes.Data.DataPendientes
import com.example.app_market.R

class DataPendientesAdapter (private val ListPendientes: List<DataPendientes>,private val OnClickListener:(DataPendientes)->Unit) : RecyclerView.Adapter<DataPendientesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataPendientesViewHolder {
      val LayoutInflater= LayoutInflater.from(parent.context)
        return DataPendientesViewHolder(LayoutInflater.inflate(R.layout.item_datapendientes,parent,false))
    }

    override fun onBindViewHolder(holder: DataPendientesViewHolder, position: Int) {
        val item=ListPendientes[position]
        holder.render(item,OnClickListener)
    }


    override fun getItemCount(): Int = ListPendientes.size



}