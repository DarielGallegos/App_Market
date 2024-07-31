package com.example.app_market.Administracion.Entregados

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.Administracion.DashBoardAdministrador
import com.example.app_market.Administracion.Entregados.Adapter.DataEntregadosAdapter
import com.example.app_market.Administracion.Pendientes.DetallesPedidos
import com.example.app_market.R
import com.example.app_market.databinding.ActivityPedidosEntregadosBinding
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned
import service.impl.AdministracionPedidosServiceImpl
import view.AdministracionPedidosView

class Pedidos_Entregados : AppCompatActivity(), AdministracionPedidosView {

    private lateinit var binding: ActivityPedidosEntregadosBinding
    private val service = AdministracionPedidosServiceImpl(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosEntregadosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener {
            finish()
        }
        service.getPedidosEntregados()
    }

    private fun OnItemSelected(dataEntregados: CabeceraPedidosUnsignedAndSigned){
        val intent = Intent(this@Pedidos_Entregados, DetallesPedidos::class.java)
        intent.putExtra("NumeroPedido", dataEntregados.pedido_numero)
        startActivity(intent)
    }

    override fun initReciclerView(list: List<CabeceraPedidosUnsignedAndSigned>) {
        binding.recyclerPedEnt.layoutManager = LinearLayoutManager(this)
        binding.recyclerPedEnt.adapter =
            DataEntregadosAdapter(list){ dataEntregados-> OnItemSelected(dataEntregados)}
    }
}