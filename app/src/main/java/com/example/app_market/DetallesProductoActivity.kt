package com.example.app_market

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.app_market.databinding.ActivityDetallesProductoBinding
import utils.Converters

class DetallesProductoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesProductoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetallesProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val nombre = extras.getString("nombre")
            val precio = extras.getFloat("precio", 0.0f) // 0 es el valor por defecto si no se encuentra la clave
            val foto = extras.getString("foto")
            val marca= extras.getString("marca")

            binding.txtnombreProduc.text = nombre
            binding.txtprecio.text = precio.toString()
            binding.txtmarca.text = marca

        }

        //binding.detalleProducto.setImageBitmap(Converters().base64ToBitmap(foto))
        // Set immersive fullscreen mode
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}
