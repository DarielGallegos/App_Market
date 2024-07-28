package com.example.app_market.Administracion.Entregados

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_market.R
import com.example.app_market.databinding.ActivityDetallesEntregadosBinding

class DetallesEntregados : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesEntregadosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesEntregadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            val intent = Intent(this@DetallesEntregados, Pedidos_Entregados::class.java)
            startActivity(intent)
        }
    }
}