package com.example.app_market

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.app_market.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import storage.StoragePreferences

class DashboardClient : AppCompatActivity() {
    private lateinit var btnShowPedidos: CardView
    private lateinit var btnNuevoPedido: CardView
    private lateinit var btnLogout: LinearLayout
    private val preference = StoragePreferences.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard_client)
        btnLogout = findViewById(R.id.btcerrar)
        btnNuevoPedido = findViewById(R.id.card_view_nuevo_pedido)

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

        btnLogout.setOnClickListener {
            // Crear y mostrar el AlertDialog
            val artDialogBuilder = AlertDialog.Builder(this@DashboardClient)
            artDialogBuilder.setMessage("¿Desea cerrar sesión?")
                .setCancelable(true)
                .setPositiveButton("Sí") { _, _ ->
                    // Borrar credenciales y cerrar la actividad
                    lifecycleScope.launch(Dispatchers.IO) {
                        preference.deleteCredentials()
                        withContext(Dispatchers.Main) {
                            finish()
                        }
                    }
                }
                .setNegativeButton("No") { _, _ ->
                    Toast.makeText(this@DashboardClient, "Clicked No", Toast.LENGTH_SHORT).show()
                }

            val alertDialogBox = artDialogBuilder.create()
            alertDialogBox.show()
        }

        btnNuevoPedido.setOnClickListener {
            val intent = Intent(this, CatalogoProductosActivity::class.java)
            startActivity(intent)

            lifecycleScope.launch(Dispatchers.IO) {
                preference.getCredentiales().collect {
                    withContext(Dispatchers.Main) {
                        if (it.id == null) {
                            val intent = Intent(this@DashboardClient, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
    }
}
