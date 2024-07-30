package com.example.app_market.repartidores.PedidosDisponibles

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.R
import com.example.app_market.databinding.ActivityPedidosDisponiblesBinding
import com.example.app_market.repartidores.DashBoardRepartidoresActivity
import com.example.app_market.repartidores.PedidosDisponibles.Adapter.DataDisponibleAdapter
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned
import service.impl.PedidosDisponiblesServiceImpl
import view.PedidosDisponiblesView

class Pedidos_Disponibles : AppCompatActivity(), PedidosDisponiblesView {
    private lateinit var btnBack: ImageView
    private lateinit var binding: ActivityPedidosDisponiblesBinding
    private val service = PedidosDisponiblesServiceImpl(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosDisponiblesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        service.getPedidosDisponibles()
        btnBack = findViewById(R.id.imgBack)

       /* binding.imgBack.setOnClickListener{
            finish()
        }*/
    btnBack.setOnClickListener{
        val intent = Intent(this@Pedidos_Disponibles, DashBoardRepartidoresActivity::class.java)
        startActivity(intent)
    }
    }


    private fun OnItemSelected(DataDisponibles: CabeceraPedidosUnsignedAndSigned){
        val intent = Intent(this@Pedidos_Disponibles, PedidoDisponible::class.java)
        intent.putExtra("NumeroPedido", DataDisponibles.pedido_numero)
        startActivity(intent)
    }

    override fun initReciclerView(list: List<CabeceraPedidosUnsignedAndSigned>) {
        binding.recyclerPedDis.layoutManager = LinearLayoutManager(this)
        binding.recyclerPedDis.adapter =
            DataDisponibleAdapter(list) {OnItemSelected(it)}
    }
}
