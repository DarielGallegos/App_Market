package com.example.app_market.repartidores.ListaProductos

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.R
import com.example.app_market.databinding.ActivityListaProductosBinding
import com.example.app_market.repartidores.ListaProductos.Adapter.DataProductoAdapter
import model.dto.REQUEST.ProductosDetalle
import service.impl.ListaProductosServiceImpl
import view.ListaProductosView

class ListaProductos : AppCompatActivity(), ListaProductosView {

    private lateinit var btnBack: Button
    private val service = ListaProductosServiceImpl(this)
    private lateinit var binding: ActivityListaProductosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        service.getDetallePedido(intent.getIntExtra("NumeroPedido", 0))
        btnBack = findViewById(R.id.btnRegresar)
        btnBack.setOnClickListener{
            finish()
        }
    }

    fun OnItemSelected(DataProductos: ProductosDetalle){
    }

    override fun initRecyclerView(list: List<ProductosDetalle>) {
        binding.recyclerListaProductos.layoutManager = LinearLayoutManager(this)
        binding.recyclerListaProductos.adapter = DataProductoAdapter(list) {OnItemSelected(it)}
    }
}