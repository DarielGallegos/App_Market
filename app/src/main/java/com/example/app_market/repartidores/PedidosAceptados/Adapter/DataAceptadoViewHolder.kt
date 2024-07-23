package com.example.app_market.repartidores.PedidosAceptados.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.ItemDataaceptadoBinding
import com.example.app_market.repartidores.PedidosAceptados.DataAceptados


class DataAceptadoViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemDataaceptadoBinding.bind(view)

    fun render(DataAceptadosModel: DataAceptados, OnClickListener: (DataAceptados) -> Unit) {
        binding.numpedido.text = DataAceptadosModel.pedido
        binding.nombre.text =  DataAceptadosModel.nombre
        itemView.setOnClickListener { OnClickListener( DataAceptadosModel)
        }
    }
}