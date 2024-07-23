package com.example.app_market.repartidores

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
import com.example.app_market.R
import com.example.app_market.login.LoginActivity
import com.example.app_market.repartidores.PedidosAceptados.Pedidos_Aceptados
import com.example.app_market.repartidores.PedidosDisponibles.Pedidos_Disponibles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import storage.StoragePreferences

class DashBoardRepartidoresActivity : AppCompatActivity() {
        private lateinit var btnLogout: LinearLayout
        private lateinit var btnshowpedDis: CardView
        private lateinit var btnshowpedAct:  CardView

        private val preference = StoragePreferences.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dash_board_repartidores)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnLogout = findViewById(R.id.btcerrar)
        btnshowpedDis = findViewById(R.id.card_view_pedidoDisponible)
        btnshowpedAct= findViewById(R.id.card_view_pedido_aceptado)


        btnshowpedDis.setOnClickListener{

            val intent = Intent(this@DashBoardRepartidoresActivity, Pedidos_Disponibles::class.java)
            startActivity(intent)
        }

        btnshowpedAct.setOnClickListener{
            val intent = Intent(this@DashBoardRepartidoresActivity, Pedidos_Aceptados::class.java)
            startActivity(intent)
        }
        btnLogout.setOnClickListener{

            val artDialogBuilder = AlertDialog.Builder(this@DashBoardRepartidoresActivity)
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
                    Toast.makeText(this@DashBoardRepartidoresActivity, "Clicked No", Toast.LENGTH_SHORT).show()
                }

            val alertDialogBox = artDialogBuilder.create()
            alertDialogBox.show()
        }


        lifecycleScope.launch(Dispatchers.IO) {
            preference.getCredentiales().collect {
                withContext(Dispatchers.Main) {
                    if (it.id == null) {
                        val intent = Intent(this@DashBoardRepartidoresActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}