package com.example.app_market

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardClient : AppCompatActivity() {
    private lateinit var btnShowPedidos: CardView
    private lateinit var btnNuevoPedido: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard_client)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnShowPedidos = findViewById(R.id.card_view_pedido)
        btnNuevoPedido = findViewById(R.id.card_view_nuevo_pedido)
        btnNuevoPedido.setOnClickListener {
            val intent = Intent(this, CatalogoProductosActivity::class.java)
            startActivity(intent)
        }
    }
}