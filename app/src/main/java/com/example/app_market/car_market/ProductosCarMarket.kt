package com.example.app_market.car_market

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.R
import com.example.app_market.databinding.ActivityProductosCarMarketBinding
import model.common.Producto
import storage.DataStoreCarMarket

class ProductosCarMarket : ComponentActivity() {
    private lateinit var binding: ActivityProductosCarMarketBinding
    private lateinit var adapter: ProductosAdapter
    private lateinit var productosList: MutableList<Producto>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductosCarMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productosList = DataStoreCarMarket.CarMarket.getCarMarket()

        initRecyclerView()

        binding.btnConfirmar.setOnClickListener {
            val total = Calculos()
            val intent = Intent(this, DetallesProductos::class.java)
            intent.putExtra("TOTAL", total)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        DataStoreCarMarket.CarMarket.addCarMarket(Producto(1, "Mirinda", 15.0, 5, 75.5, "Nada"))
        DataStoreCarMarket.CarMarket.addCarMarket(Producto(2, "Coca-Cola", 12.0, 10, 50.0, "Soda"))
        DataStoreCarMarket.CarMarket.addCarMarket(Producto(3, "Pepsi", 11.0, 7, 45.0, "Soda"))
        DataStoreCarMarket.CarMarket.addCarMarket(Producto(4, "Fanta", 14.0, 8, 65.0, "Soda"))

        adapter = ProductosAdapter(
            productosList,
            { producto -> onItemSelected(producto) },
            { producto -> onItemDeleted(producto) }
        )

        binding.recyclerProductos.layoutManager = manager
        binding.recyclerProductos.adapter = adapter
    }

    private fun onItemSelected(producto: Producto) {
        Toast.makeText(this, producto.nombre, Toast.LENGTH_SHORT).show()
    }

    private fun onItemDeleted(producto: Producto) {
        DataStoreCarMarket.CarMarket.deleteCarMarket(producto)
        adapter.deleteItem(producto)
        Toast.makeText(this, "Este producto ha sido borrado", Toast.LENGTH_SHORT).show()
        adapter.notifyDataSetChanged()
    }

    private fun Calculos(): Double {
        return productosList.sumOf { it.precio * it.cantidad }
    }
}