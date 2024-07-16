package com.example.app_market.adapters

import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.app_market.databinding.ActivityCatalogoProductosBinding
import com.example.app_market.databinding.ViewProductoBinding
import model.common.Producto
import utils.Converters
import java.io.ByteArrayInputStream

class CatProductosAdapter(private val productos:List<Producto>?,  ):
    RecyclerView.Adapter<CatProductosAdapter.MyViewHolder>()
{
    class MyViewHolder(val binding: ViewProductoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(producto: Producto) {
            binding.imagen.setOnClickListener {
                Log.d("Pro adapter","click en producto")
            }

               /* Glide.with(binding.imagen)
                .asBitmap()
                .load(producto.foto!!.binaryStream)
               // .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imagen)*/

           binding.tvProNombre.text = producto.producto
            if(producto.foto != ""){
                binding.imagen.setImageBitmap(Converters().base64ToBitmap(producto.foto))
            }
        }

    }
    //Establecer una conexion con el Layout de vista de producto
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ViewProductoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
    //Obtener el total de productos
    override fun getItemCount(): Int {
        return productos!!.size
    }
    //Le pasan un producto y la posicion del producto
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val producto = productos!![position]
        holder.bind(producto)

    }
}