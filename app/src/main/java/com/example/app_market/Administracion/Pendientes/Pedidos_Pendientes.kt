package com.example.app_market.Administracion.Pendientes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.Administracion.DashBoardAdministrador
import com.example.app_market.Administracion.Pendientes.Adapter.DataPendientesAdapter
import com.example.app_market.databinding.ActivityPedidosPendientesBinding
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned
import service.impl.AdministracionPedidosServiceImpl
import view.AdministracionPedidosView

class Pedidos_Pendientes : AppCompatActivity(), AdministracionPedidosView {

    private lateinit var binding: ActivityPedidosPendientesBinding
    private val service = AdministracionPedidosServiceImpl(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosPendientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener{
            finish()
        }
        service.getPedidosPendientes()
    }

    private fun OnItemSelected(dataPendientes: CabeceraPedidosUnsignedAndSigned){
        val intent = Intent(this@Pedidos_Pendientes, DetallesPedidos::class.java)
        intent.putExtra("NumeroPedido", dataPendientes.pedido_numero)
        startActivity(intent)
    }

    override fun initReciclerView(list: List<CabeceraPedidosUnsignedAndSigned>) {
        binding.recyclerPedPend.layoutManager = LinearLayoutManager(this)
        binding.recyclerPedPend.adapter = DataPendientesAdapter(list){dataPendientes-> OnItemSelected(dataPendientes)}
    }
}