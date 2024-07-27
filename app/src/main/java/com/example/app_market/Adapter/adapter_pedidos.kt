package com.example.app_market.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.VistapedidosBinding
import model.common.Pedidos
import com.google.gson.Gson


class adapter_pedidos (private val pedidos:List<Pedidos>?,
                       private val onClickListener: (Pedidos) -> Unit):
    RecyclerView.Adapter<adapter_pedidos.MyViewHolder>()
{
    class MyViewHolder(val binding: VistapedidosBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pedidos: Pedidos, onClickListener: (Pedidos) -> Unit) {
            binding.cardViewPedido.setOnClickListener {
                onClickListener(pedidos)
            }

            binding.tvNumeroPedido.text = pedidos.pedido_numero.toString()
            binding.tvEstado.text = pedidos.estado_pedido
            binding.tvTotal.text = pedidos.total.toString()
        }

    }
    //Establecer una conexion con el Layout de vista de pedido
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = VistapedidosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
    //Obtener el total de pedidos
    override fun getItemCount(): Int {
       //t return pedidos!!.size
        return pedidos?.size ?: 0
    }
    //Le pasan un pedido y la posicion del pedido
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       //t val pedido = pedidos!![position]
        //t Log.i("Pedido ${position}", pedido.toString())
       //t holder.bind(pedido)
        pedidos?.get(position)?.let {
            holder.bind(it, onClickListener)
        }
    }
}