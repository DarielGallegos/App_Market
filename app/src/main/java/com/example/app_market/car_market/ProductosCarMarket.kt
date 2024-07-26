package com.example.app_market.car_market

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.databinding.ActivityProductosCarMarketBinding
import model.common.CarMarketProducto
import model.common.Producto
import storage.DataStoreCarMarket

class ProductosCarMarket : AppCompatActivity() {
    private lateinit var binding: ActivityProductosCarMarketBinding
    private lateinit var adapter: ProductosAdapter
    private lateinit var productosList: MutableList<Producto>
    private val envio = 50.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductosCarMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productosList = DataStoreCarMarket.CarMarket.getCarMarket()

        initRecyclerView()

        setSupportActionBar(binding.detProToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.btnConfirmar.setOnClickListener {
            val subtotal = calculoSubtotal()
            val impuesto = subtotal * 0.15
            val total = subtotal + impuesto + envio

            val intent = Intent(this, DetallesProductosFinancieros::class.java)
            intent.putExtra("SUBTOTAL", subtotal)
            intent.putExtra("IMPUESTO", impuesto)
            intent.putExtra("ENVIO", envio)
            intent.putExtra("TOTAL", total)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        adapter = ProductosAdapter(
            productosList,
            { producto -> onItemSelected(producto) },
            { producto -> onItemDeleted(producto) }
        )
        binding.recyclerProductos.layoutManager = manager
        binding.recyclerProductos.adapter = adapter
    }

    private fun calculoSubtotal(): Double {
        var subtotal = 0.0
        for (producto in productosList) {
            subtotal += producto.precio * producto.cantidad
        }
        return subtotal
    }


    private fun onItemSelected(producto: Producto) {
        Toast.makeText(this, producto.producto, Toast.LENGTH_SHORT).show()
    }



    private fun onItemDeleted(producto: Producto) {
        DataStoreCarMarket.CarMarket.deleteCarMarket(producto)
        adapter.deleteItem(producto)
        Toast.makeText(this, "Este producto ha sido borrado", Toast.LENGTH_SHORT).show()
        adapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}