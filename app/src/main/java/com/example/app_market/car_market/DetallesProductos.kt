package com.example.app_market.car_market

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_market.R

class DetallesProductos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_productos)

        val total = intent.getDoubleExtra("TOTAL", 0.0)
        val impuesto = total * 0.15
        val totalSum = total + impuesto
        val txtTotal = findViewById<EditText>(R.id.txttotal)
        txtTotal.setText(totalSum.toString())
        val txtImpuesto = findViewById<EditText>(R.id.txtimpuesto)
        txtImpuesto.setText(impuesto.toString())
        val txtTotalImpuesto = findViewById<EditText>(R.id.txttotal_imp)
        txtTotalImpuesto.setText(impuesto.toString())

    }
}