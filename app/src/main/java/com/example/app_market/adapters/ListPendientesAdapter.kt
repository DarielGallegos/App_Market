package com.example.app_market.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.DetallesProductoActivity
import com.example.app_market.ValoracionPedidoActivity
import com.example.app_market.databinding.ViewListaPendientesBinding

import com.example.app_market.databinding.ViewProductoBinding
import model.common.PedidoValorar


class ListPendientesAdapter (val context: Context, private var pedidos:List<PedidoValorar>? ):
   RecyclerView.Adapter<ListPendientesAdapter.MyViewHolder>()

{

    fun setData(pedidos:List<PedidoValorar>){
        this.pedidos = pedidos
        notifyDataSetChanged()
    }

    class MyViewHolder(val context: Context, val binding:  ViewListaPendientesBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pedidos: PedidoValorar) {
            binding.cardPedidos.setOnClickListener {

                val intent = Intent(context, ValoracionPedidoActivity::class.java)
                intent.putExtra("pedidoNumero",pedidos.pedidoNumero)
                intent.putExtra("Cliente",pedidos.Cliente)
                intent.putExtra("usuario",pedidos.usuario)
                intent.putExtra("total",pedidos.total)
                intent.putExtra("estado",pedidos.estado)

                context.startActivity(intent)

            }

            binding.numPedido.text = pedidos.pedidoNumero.toString()
            binding.cliente.text = pedidos.Cliente
            binding.usuario.text = pedidos.usuario

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ViewListaPendientesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(context, binding)
    }

    override fun getItemCount(): Int {
        return pedidos!!.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pedidosposicion= pedidos!![position]
        holder.bind(pedidosposicion)

    }
}


