package com.example.app_market

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import client.Client
import client.services.PedidoService
import client.services.RepartidorService
import com.example.app_market.adapters.ListPendientesAdapter
import com.example.app_market.databinding.ActivityCatalogoProductosBinding
import com.example.app_market.databinding.ActivityListaPendienteValoracionBinding
import com.google.gson.Gson
import controller.impl.PedidosAceptadosControllerImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.common.ApiResponseBody
import model.common.PedidoValorar
import model.common.Producto
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.impl.PedidosSinValorarServicelmpl
import storage.StoragePreferences

class ListaPendienteValoracionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaPendienteValoracionBinding
    private val storage = StoragePreferences.getInstance(this)
    private val service = PedidosSinValorarServicelmpl(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityListaPendienteValoracionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pedidos: List<PedidoValorar>? = null
        var adapter: ListPendientesAdapter? = null

        val rec_view = binding.listaPendientes
        rec_view.layoutManager = LinearLayoutManager(this)

        binding.Inicio.setOnClickListener{

            val intent = Intent(this, DashboardClient::class.java)
            startActivity(intent)
        }

        lifecycleScope.launch(Dispatchers.IO){
            storage.getCredentiales().collect {
                if(it.id != null){
                    service.getPedidosSValorar(it.id){ pedidos ->
                        binding
                            .listaPendientes
                            .adapter = ListPendientesAdapter(this@ListaPendienteValoracionActivity,pedidos)
                    }
                }
            }
        }

    }
}