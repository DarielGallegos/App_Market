package com.example.app_market.car_market

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.R
import model.common.CarMarketProducto

class ProductosAdapter(
    private val productosList: MutableList<CarMarketProducto>,
    private val onClickListener: (CarMarketProducto) -> Unit,
    private val onDeleteListener: (CarMarketProducto) -> Unit
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

    fun deleteItem(carMarketProducto: CarMarketProducto){
        val position = productosList.indexOf(carMarketProducto)
        if (position != -1) {
            productosList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemChanged(position, productosList.size)
        }
    }
}