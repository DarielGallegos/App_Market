package com.example.app_market

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_market.databinding.ActivityDetallesProductoBinding
import model.common.Producto
import storage.DataStoreCarMarket
import utils.Converters

class DetallesProductoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesProductoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var cantidad = 1
        binding.productoCantidad.text = cantidad.toString()

        var toolbar = binding.detProToolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true);

        val extras = intent.extras
        if (extras != null) {
            val nombre = extras.getString("nombre")
            val precio = extras.getFloat("precio", 0.0f)
            val foto = extras.getString("foto")
            val marca= extras.getString("marca")
            val descripcion = extras.getString("descripcion")

            binding.txtnombreProduc.text = nombre
            binding.txtprecio.text = precio.toString()
            binding.txtmarca.text = marca
            binding.detalleProducto.setImageBitmap(Converters().base64ToBitmap(foto!!))
            binding.txtdescripcion.text = descripcion
        }

        binding.agregarProducto.setOnClickListener{
            cantidad++
            binding.productoCantidad.text = cantidad.toString()
        }

        binding.removerProducto.setOnClickListener{
            if (cantidad > 1){
                cantidad--
                binding.productoCantidad.text = cantidad.toString()
            }
        }

        binding.btnaAdirCarrito.setOnClickListener(){
            try {
                DataStoreCarMarket.CarMarket.addCarMarket(producto =Producto())
                Toast.makeText(this, "Producto añadido al carrito", Toast.LENGTH_SHORT).show()
            }
            catch (e: Exception){
                Log.e("Error", "Error al añadir el producto al carrito", e)
                Toast.makeText(this, "Error al añadir el producto al carrito", Toast.LENGTH_SHORT).show()
            }

        }

        // Set immersive fullscreen mode
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
