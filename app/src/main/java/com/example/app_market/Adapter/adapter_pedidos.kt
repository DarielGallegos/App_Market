package com.example.app_market.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.databinding.VistapedidosBinding
import model.common.Pedidos


class adapter_pedidos (private val pedidos:List<Pedidos>?,  ):
    RecyclerView.Adapter<adapter_pedidos.MyViewHolder>()
{
    class MyViewHolder(val binding: VistapedidosBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pedidos: Pedidos) {
            binding.cardViewPedido.setOnClickListener {
                Log.d("Pro adapter","click en pedido")
            }

            binding.tvNumeroPedido.text = pedidos.numPedido.toString()
            binding.tvEstado.text = pedidos.estadoPedido
            binding.tvTotal.text = pedidos.total.toString()
        }

    }
    //Establecer una conexion con el Layout de vista de pedido
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = VistapedidosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
    //Obtener el total de productos
    override fun getItemCount(): Int {
        return pedidos!!.size
    }
    //Le pasan un producto y la posicion del pedido
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pedidos = pedidos!![position]
        holder.bind(pedidos)

    }
}