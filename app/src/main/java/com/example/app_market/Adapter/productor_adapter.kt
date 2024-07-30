package com.example.app_market.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.R
import com.example.app_market.databinding.AdapterProductoBinding
import com.example.app_market.databinding.VistapedidosBinding
import model.common.Pedidos
import model.dto.REQUEST.ProductosDetalle
import utils.Converters

class productor_adapter(private val productosList: List<ProductosDetalle>) :
    RecyclerView.Adapter<productor_adapter.ProductoViewHolder>() {

    class ProductoViewHolder(val binding: AdapterProductoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(producto: ProductosDetalle) {
            binding.tvProducto.text = "Producto: ${producto.nombreProducto}"
            binding.tvPrecio.text = "Precio: L.${producto.precio}"
            binding.tvCantidad.text = "Cantidad: ${producto.cantidad}"
           // binding.tvMonto.text = "Monto: ${producto.monto}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = AdapterProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productosList.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        holder.bind(productosList[position])
    }
}
