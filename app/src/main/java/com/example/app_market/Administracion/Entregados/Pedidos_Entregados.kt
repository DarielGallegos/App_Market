package com.example.app_market.Administracion.Entregados

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.Administracion.DashBoardAdministrador
import com.example.app_market.Administracion.Entregados.Adapter.DataEntregadosAdapter
import com.example.app_market.Administracion.Entregados.Data.DataEntregados
import com.example.app_market.Administracion.Entregados.Data.DataEntregadosProvider
import com.example.app_market.R
import com.example.app_market.databinding.ActivityPedidosEntregadosBinding

class Pedidos_Entregados : AppCompatActivity() {

    private lateinit var binding: ActivityPedidosEntregadosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosEntregadosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initReciclerView()
        binding.imgBack.setOnClickListener {
            val intent = Intent(this@Pedidos_Entregados, DashBoardAdministrador::class.java)
            startActivity(intent)
        }
    }
    private fun    initReciclerView(){
    binding.recyclerPedEnt.layoutManager = LinearLayoutManager(this)
    binding.recyclerPedEnt.adapter =
        DataEntregadosAdapter(DataEntregadosProvider.DataListEntregados){ dataEntregados-> OnItemSelected(dataEntregados)}
    }

    private fun OnItemSelected(dataEntregados: DataEntregados){
        val intent = Intent(this@Pedidos_Entregados, DetallesEntregados::class.java)
        startActivity(intent)
    }
}