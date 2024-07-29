package com.example.app_market.Administracion.Pendientes.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.ItemDatapendientesBinding
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned

class DataPendientesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemDatapendientesBinding.bind(view)

    fun render(DataPendientesModel: CabeceraPedidosUnsignedAndSigned, OnclickListener: (CabeceraPedidosUnsignedAndSigned) -> Unit) {
        binding.numpendiente.text=DataPendientesModel.pedido_numero.toString()
        binding.nombrependiente.text=DataPendientesModel.cliente
        itemView.setOnClickListener { OnclickListener(DataPendientesModel) }
    }
}