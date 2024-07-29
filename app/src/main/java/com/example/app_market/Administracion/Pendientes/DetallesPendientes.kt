package com.example.app_market.Administracion.Pendientes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_market.R
import com.example.app_market.databinding.ActivityDetallesPendientesBinding

class DetallesPendientes : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesPendientesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesPendientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            val intent = Intent(this@DetallesPendientes, Pedidos_Pendientes::class.java)
            startActivity(intent)

        }
    }
}