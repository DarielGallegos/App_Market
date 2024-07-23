package com.example.app_market.repartidores.PedidosDisponibles

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech.OnInitListener
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.R
import com.example.app_market.databinding.ActivityPedidosDisponiblesBinding
import com.example.app_market.repartidores.DashBoardRepartidoresActivity
import com.example.app_market.repartidores.PedidosAceptados.PedidoAceptadoDetalle
import com.example.app_market.repartidores.PedidosDisponibles.Adapter.DataDisponibleAdapter

class Pedidos_Disponibles : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var btnPed: ImageView
    private lateinit var binding: ActivityPedidosDisponiblesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosDisponiblesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initReciclerView()

        //btnBack = findViewById(R.id.imgBack)
       // btnPed = findViewById(R.id.verDetalles)
        binding.imgBack.setOnClickListener{

            val intent = Intent(this@Pedidos_Disponibles, DashBoardRepartidoresActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initReciclerView() {
        binding.recyclerPedDis.layoutManager = LinearLayoutManager(this)
        binding.recyclerPedDis.adapter =
            DataDisponibleAdapter(DataDisponibleProvider.DataListDisponible) {DataDisponibles -> OnItemSelected(DataDisponibles)}
    }

    private fun OnItemSelected(DataDisponibles: DataDisponibles){
        //Toast.makeText(this, DataDisponibles.pedido, Toast.LENGTH_SHORT).show()
        val intent = Intent(this@Pedidos_Disponibles, PedidoDisponible::class.java)
        startActivity(intent)
    }

}
