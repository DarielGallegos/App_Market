package com.example.app_market.repartidores.PedidosAceptados

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.R
import com.example.app_market.databinding.ActivityPedidosAceptadosBinding
import com.example.app_market.databinding.ActivityPedidosDisponiblesBinding
import com.example.app_market.repartidores.DashBoardRepartidoresActivity
import com.example.app_market.repartidores.PedidosAceptados.Adapter.DataAceptadoAdapter
import com.example.app_market.repartidores.PedidosDisponibles.Adapter.DataDisponibleAdapter
import com.example.app_market.repartidores.PedidosDisponibles.DataDisponibleProvider
import com.example.app_market.repartidores.PedidosDisponibles.DataDisponibles

class Pedidos_Aceptados : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var btnPed: ImageView
    private lateinit var binding: ActivityPedidosAceptadosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosAceptadosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        binding.imgBack.setOnClickListener {
            val intent = Intent(this@Pedidos_Aceptados, DashBoardRepartidoresActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerPedAcep.layoutManager = LinearLayoutManager(this)
        binding.recyclerPedAcep.adapter = DataAceptadoAdapter(DataAceptadoProvider.DataListAceptados) { dataAceptados ->
            onItemSelected(dataAceptados)
        }
    }

    private fun onItemSelected(dataAceptados: DataAceptados) {
        val intent = Intent(this@Pedidos_Aceptados, PedidoAceptadoDetalle::class.java)
        startActivity(intent)
    }
}