package com.example.app_market.repartidores.ListaProductos

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.R
import com.example.app_market.databinding.ActivityListaProductosBinding
import com.example.app_market.repartidores.ListaProductos.Adapter.DataProductoAdapter
import com.example.app_market.repartidores.PedidosDisponibles.Adapter.DataDisponibleAdapter
import com.example.app_market.repartidores.PedidosDisponibles.DataDisponibleProvider
import com.example.app_market.repartidores.PedidosDisponibles.DataDisponibles

class ListaProductos : AppCompatActivity() {

    private lateinit var btnBack: ImageView

    private lateinit var binding: ActivityListaProductosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initReciclerView()
    }
    fun initReciclerView() {
        binding.recyclerListaProductos.layoutManager = LinearLayoutManager(this)
        binding.recyclerListaProductos.adapter =
            DataProductoAdapter(DataProductoProvider.DataListProducto) { DataProductos -> OnItemSelected(DataProductos)}
    }

    fun OnItemSelected(DataProductos: DataProductos){
        Toast.makeText(this, DataProductos.producto, Toast.LENGTH_SHORT).show()
    }
}