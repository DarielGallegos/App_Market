package com.example.app_market.Administracion.Entregados.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.Administracion.Entregados.Data.DataEntregados
import com.example.app_market.databinding.ItemDataentregadosBinding

class DataEntregradosViewHolder (view: View): RecyclerView.ViewHolder(view) {

    val binding= ItemDataentregadosBinding.bind(view)

    fun render(DataEntregadosModel: DataEntregados, onClickListener:(DataEntregados)->Unit){
        binding.numentregados.text = DataEntregadosModel.pedido
        binding.nombreentregados.text = DataEntregadosModel.nombre
        itemView.setOnClickListener{onClickListener(DataEntregadosModel)}
    }

}