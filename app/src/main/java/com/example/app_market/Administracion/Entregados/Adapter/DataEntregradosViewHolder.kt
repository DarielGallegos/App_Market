package com.example.app_market.Administracion.Entregados.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.ItemDataentregadosBinding
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned

class DataEntregradosViewHolder (view: View): RecyclerView.ViewHolder(view) {

    val binding= ItemDataentregadosBinding.bind(view)

    fun render(DataEntregadosModel: CabeceraPedidosUnsignedAndSigned, onClickListener:(CabeceraPedidosUnsignedAndSigned)->Unit){
        binding.numentregados.text = DataEntregadosModel.pedido_numero.toString()
        binding.nombreentregados.text = DataEntregadosModel.cliente
        itemView.setOnClickListener{onClickListener(DataEntregadosModel)}
    }

}