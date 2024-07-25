package com.example.app_market.car_market

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.databinding.ActivityProductosCarMarketBinding
import model.common.CarMarketProducto
import storage.DataStoreCarMarket

class ProductosCarMarket : ComponentActivity() {
    private lateinit var binding: ActivityProductosCarMarketBinding
    private lateinit var adapter: ProductosAdapter
    private lateinit var productosList: MutableList<CarMarketProducto>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductosCarMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productosList = DataStoreCarMarket.CarMarket.getCarMarket()

        initRecyclerView()

        binding.btnConfirmar.setOnClickListener {
            val total = Calculos()
            val intent = Intent(this, DetallesProductosFinancieros::class.java)
            intent.putExtra("TOTAL", total)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        DataStoreCarMarket.CarMarket.addCarMarket(CarMarketProducto(1, "Mirinda", 15.0, 5, 75.5, "Nada"))
        DataStoreCarMarket.CarMarket.addCarMarket(CarMarketProducto(2, "Coca-Cola", 12.0, 10, 50.0, "Soda"))
        DataStoreCarMarket.CarMarket.addCarMarket(CarMarketProducto(3, "Pepsi", 11.0, 7, 45.0, "Soda"))
        DataStoreCarMarket.CarMarket.addCarMarket(CarMarketProducto(4, "Fanta", 14.0, 8, 65.0, "Soda"))

        adapter = ProductosAdapter(
            productosList,
            { producto -> onItemSelected(producto) },
            { producto -> onItemDeleted(producto) }
        )

        binding.recyclerProductos.layoutManager = manager
        binding.recyclerProductos.adapter = adapter
    }

    private fun onItemSelected(carMarketProducto: CarMarketProducto) {
        Toast.makeText(this, carMarketProducto.nombre, Toast.LENGTH_SHORT).show()
    }

    private fun onItemDeleted(carMarketProducto: CarMarketProducto) {
        DataStoreCarMarket.CarMarket.deleteCarMarket(carMarketProducto)
        adapter.deleteItem(carMarketProducto)
        Toast.makeText(this, "Este producto ha sido borrado", Toast.LENGTH_SHORT).show()
        adapter.notifyDataSetChanged()
    }

    private fun Calculos(): Double {
        return productosList.sumOf { it.precio * it.cantidad }
    }
}