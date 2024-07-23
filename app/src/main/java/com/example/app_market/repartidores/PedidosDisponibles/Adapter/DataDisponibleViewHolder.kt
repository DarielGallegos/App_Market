package com.example.app_market.repartidores.PedidosDisponibles.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.ItemDatadisponibleBinding
import com.example.app_market.repartidores.PedidosDisponibles.DataDisponibles

class DataDisponibleViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val binding = ItemDatadisponibleBinding.bind(view)


    fun render(DataDisponiblesModel: DataDisponibles, OnClickListener: (DataDisponibles) -> Unit) {
        binding.numpedido.text = DataDisponiblesModel.pedido
        binding.nombre.text = DataDisponiblesModel.nombre
        itemView.setOnClickListener { OnClickListener(DataDisponiblesModel)
        }
    }
}