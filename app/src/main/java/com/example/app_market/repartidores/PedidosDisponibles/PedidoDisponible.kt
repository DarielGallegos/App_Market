package com.example.app_market.repartidores.PedidosDisponibles

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_market.R
import com.example.app_market.repartidores.ListaProductos.ListaProductos

class PedidoDisponible : AppCompatActivity() {

    private lateinit var btnVerProductos: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pedido_disponible_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnVerProductos = findViewById(R.id.btnVerProductos)


        btnVerProductos.setOnClickListener{
            val intent = Intent(this@PedidoDisponible, ListaProductos::class.java)
            startActivity(intent)
        }
    }
}