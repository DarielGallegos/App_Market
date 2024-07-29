package com.example.app_market.Administracion.Pendientes.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.Administracion.Pendientes.Data.DataPendientes
import com.example.app_market.databinding.ItemDatapendientesBinding

class DataPendientesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemDatapendientesBinding.bind(view)

    fun render(DataPendientesModel: DataPendientes, OnclickListener: (DataPendientes) -> Unit) {
        binding.numpendiente.text=DataPendientesModel.pedido
        binding.nombrependiente.text=DataPendientesModel.nombre
        itemView.setOnClickListener { OnclickListener(DataPendientesModel) }
    }
}