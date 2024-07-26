package com.example.app_market.repartidores.PedidosAceptados.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.ItemDataaceptadoBinding
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned


class DataAceptadoViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemDataaceptadoBinding.bind(view)

    fun render(DataAceptadosModel: CabeceraPedidosUnsignedAndSigned, OnClickListener: (CabeceraPedidosUnsignedAndSigned) -> Unit) {
        binding.numpedido.text = DataAceptadosModel.pedido_numero.toString()
        binding.nombre.text =  DataAceptadosModel.cliente
        itemView.setOnClickListener { OnClickListener( DataAceptadosModel)
        }
    }
}