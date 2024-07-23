package com.example.app_market.repartidores.PedidosDisponibles

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_market.R
import com.example.app_market.databinding.ActivityMainBinding
import com.example.app_market.databinding.ActivityPedidosDisponiblesBinding
import com.example.app_market.repartidores.DashBoardRepartidoresActivity
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

        btnBack = findViewById(R.id.imgBack)
        btnPed = findViewById(R.id.imagearrow2)
        btnBack.setOnClickListener{

            val intent = Intent(this@Pedidos_Disponibles, DashBoardRepartidoresActivity::class.java)
            startActivity(intent)
        }

        btnPed.setOnClickListener{
            //Detalles de pedido
        }
    }

    fun initReciclerView() {
        binding.recyclerPedDis.layoutManager = LinearLayoutManager(this)
        binding.recyclerPedDis.adapter =
            DataDisponibleAdapter(DataDisponibleProvider.DataListDisponible) {DataDisponibles -> OnItemSelected(DataDisponibles)}
    }

    fun OnItemSelected(DataDisponibles: DataDisponibles){
        Toast.makeText(this, DataDisponibles.pedido, Toast.LENGTH_SHORT).show()
    }
}
