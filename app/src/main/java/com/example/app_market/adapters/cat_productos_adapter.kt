package com.example.app_market.adapters

import android.content.Context
import android.content.Intent
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.app_market.DetallesProductoActivity
import com.example.app_market.databinding.ActivityCatalogoProductosBinding
import com.example.app_market.databinding.ViewProductoBinding
import model.common.Producto
import utils.Converters
import java.io.ByteArrayInputStream

class CatProductosAdapter(val context: Context, private val productos:List<Producto>?,  ):
    RecyclerView.Adapter<CatProductosAdapter.MyViewHolder>()
{
    class MyViewHolder(val context: Context, val binding: ViewProductoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(producto: Producto) {
            binding.imagen.setOnClickListener {
                Log.d("Pro adapter","click en producto")

                    val intent = Intent(context, DetallesProductoActivity::class.java)
                    intent.putExtra("nombre",producto.producto)
                    intent.putExtra("precio",producto.precio)
                    intent.putExtra("foto",producto.foto)
                    intent.putExtra("marca",producto.marca)
                    context.startActivity(intent)

            }

           binding.tvProNombre.text = producto.producto

            if(producto.foto != ""){
                binding.imagen.setImageBitmap(Converters().base64ToBitmap(producto.foto))
            }
        }

    }
    //Establecer una conexion con el Layout de vista de producto
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ViewProductoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(context, binding)
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