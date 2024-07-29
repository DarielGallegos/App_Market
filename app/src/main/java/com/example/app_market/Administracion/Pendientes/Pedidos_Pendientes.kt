package com.example.app_market.Administracion.Pendientes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.Administracion.DashBoardAdministrador
import com.example.app_market.Administracion.Pendientes.Adapter.DataPendientesAdapter
import com.example.app_market.Administracion.Pendientes.Data.DataPendientes
import com.example.app_market.Administracion.Pendientes.Data.DataPendientesProvider
import com.example.app_market.R
import com.example.app_market.databinding.ActivityMainBinding
import com.example.app_market.databinding.ActivityPedidosPendientesBinding

class Pedidos_Pendientes : AppCompatActivity() {

    private lateinit var binding: ActivityPedidosPendientesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosPendientesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initReciclerView()

        binding.imgBack.setOnClickListener{
            val intent = Intent(this@Pedidos_Pendientes, DashBoardAdministrador::class.java)
            startActivity(intent)
        }
    }

    private fun initReciclerView(){
        binding.recyclerPedPend.layoutManager = LinearLayoutManager(this)
        binding.recyclerPedPend.adapter = DataPendientesAdapter(DataPendientesProvider.DataListPendientes){dataPendientes-> OnItemSelected(dataPendientes)}
    }

    private fun OnItemSelected(dataPendientes: DataPendientes){
        val intent = Intent(this@Pedidos_Pendientes, DetallesPendientes::class.java)
        startActivity(intent)
    }
}