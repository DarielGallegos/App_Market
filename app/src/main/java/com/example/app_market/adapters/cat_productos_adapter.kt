package com.example.app_market.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.DetallesProductoActivity
import com.example.app_market.databinding.ViewProductoBinding
import model.common.Producto
import utils.Converters


class CatProductosAdapter(val context: Context, private var productos:List<Producto>?,  ):
    RecyclerView.Adapter<CatProductosAdapter.MyViewHolder>()
{

        fun setData(productos:List<Producto>){
            this.productos = productos
        }

    class MyViewHolder(val context: Context, val binding: ViewProductoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(producto: Producto) {
            binding.cardProducto.setOnClickListener {
                Log.d("Pro adapter","click en producto")

                    val intent = Intent(context, DetallesProductoActivity::class.java)
                    intent.putExtra("nombre",producto.producto)
                    intent.putExtra("precio",producto.precio)
                    intent.putExtra("foto",producto.foto)

                    intent.putExtra("marca",producto.marca)
                    intent.putExtra("descripcion",producto.descripcion)

                    context.startActivity(intent)

            }

           binding.tvProNombre.text = producto.producto
            binding.tvProPrecio.text = producto.precio.toString()

            if(producto.foto != ""){
                binding.imagen.setImageBitmap(Converters().base64ToBitmap(producto.foto))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ViewProductoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(context, binding)
    }

    override fun getItemCount(): Int {
       return productos!!.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val producto = productos!![position]
        holder.bind(producto)

    }
}