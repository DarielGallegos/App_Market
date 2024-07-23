package com.example.app_market.car_market

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.R
import model.common.Producto

class ProductosAdapter(
    private val productosList: MutableList<Producto>,
    private val onClickListener: (Producto) -> Unit,
    private val onDeleteListener: (Producto) -> Unit
    ) : RecyclerView.Adapter<ProductosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductosViewHolder(layoutInflater.inflate(R.layout.item_productos, parent, false))
    }

    override fun getItemCount(): Int = productosList.size

    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        val item = productosList[position]
        holder.render(item, onClickListener, onDeleteListener)
    }

    fun deleteItem(producto: Producto){
        val position = productosList.indexOf(producto)
        if (position != -1) {
            productosList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemChanged(position, productosList.size)
        }
    }
}