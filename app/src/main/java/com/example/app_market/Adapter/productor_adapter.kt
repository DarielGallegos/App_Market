package com.example.app_market.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.R
import com.example.app_market.databinding.ItemProductoBinding
import com.example.app_market.databinding.VistapedidosBinding
import model.common.Pedidos
import model.dto.REQUEST.ProductosDetalle
import utils.Converters

class productor_adapter(private val productosList: List<ProductosDetalle>) :
    RecyclerView.Adapter<productor_adapter.ProductoViewHolder>() {

    class ProductoViewHolder(val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(producto: ProductosDetalle) {
            binding.tvProducto.text = "Producto: ${producto.nombreProductos}"
            binding.tvPrecio.text = "Precio: ${producto.precio}"
            binding.tvCantidad.text = "Cantidad: ${producto.cantidad}"
            binding.tvMonto.text = "Monto: ${producto.monto}"

            //val bitmap = Converters().base64ToBitmap(producto.imagen)
          //if (bitmap != null) {
              //  binding.ivProducto.setImageBitmap(bitmap)
                if(producto.imagen != ""){
                    binding.ivProducto.setImageBitmap(Converters().base64ToBitmap(producto.imagen))
            } else {
                binding.ivProducto.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productosList.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        holder.bind(productosList[position])
    }
}
        /*

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productosList[position]
        holder.nombreProducto.text = producto.nombreProductos
        holder.precioProducto.text = "Precio: ${producto.precio}"
        holder.cantidadProducto.text = "Cantidad: ${producto.cantidad}"
        holder.montoProducto.text = "Monto: ${producto.monto}"
    }

    override fun getItemCount() = productosList.size

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreProducto: TextView = itemView.findViewById(R.id.tvProducto)
        val precioProducto: TextView = itemView.findViewById(R.id.tvPrecio)
        val cantidadProducto: TextView = itemView.findViewById(R.id.tvCantidad)
        val montoProducto: TextView = itemView.findViewById(R.id.tvMonto)
    }
}*/