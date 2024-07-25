package com.example.app_market.repartidores.PedidosDisponibles.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.ItemDatadisponibleBinding
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned

class DataDisponibleViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val binding = ItemDatadisponibleBinding.bind(view)


    fun render(DataDisponiblesModel: CabeceraPedidosUnsignedAndSigned, OnClickListener: (CabeceraPedidosUnsignedAndSigned) -> Unit) {
        binding.numpedido.text = "${DataDisponiblesModel.pedido_numero}"
        binding.nombre.text = DataDisponiblesModel.cliente
        itemView.setOnClickListener { OnClickListener(DataDisponiblesModel)
        }
    }
}