package com.example.app_market.repartidores.PedidosAceptados

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_market.databinding.ActivityPedidosAceptadosBinding
import com.example.app_market.repartidores.PedidosAceptados.Adapter.DataAceptadoAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned
import service.impl.PedidosAceptadosServiceImpl
import storage.StoragePreferences
import view.PedidosAceptadoView

class Pedidos_Aceptados : AppCompatActivity(), PedidosAceptadoView {

    private lateinit var btnBack: ImageView
    private lateinit var btnPed: ImageView
    private lateinit var binding: ActivityPedidosAceptadosBinding
    private val service = PedidosAceptadosServiceImpl(this)
    private val storage = StoragePreferences.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosAceptadosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch(Dispatchers.IO){
            storage.getCredentiales().collect {
                if(it.id != null){
                    service.getPedidosAceptados(it.id)
                }
            }
        }
        binding.imgBack.setOnClickListener { finish() }
    }


    private fun onItemSelected(dataAceptados: CabeceraPedidosUnsignedAndSigned) {
        val intent = Intent(this@Pedidos_Aceptados, PedidoAceptadoDetalle::class.java)
        intent.putExtra("NumeroPedido", dataAceptados.pedido_numero)
        startActivity(intent)
    }

    override fun initReciclerView(list: List<CabeceraPedidosUnsignedAndSigned>) {
        binding.recyclerPedAcep.layoutManager = LinearLayoutManager(this)
        binding.recyclerPedAcep.adapter = DataAceptadoAdapter(list) { onItemSelected(it) }
    }
}