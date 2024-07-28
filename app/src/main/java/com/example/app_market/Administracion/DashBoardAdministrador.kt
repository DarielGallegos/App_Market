package com.example.app_market.Administracion

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
import com.example.app_market.Administracion.Entregados.Pedidos_Entregados
import com.example.app_market.Administracion.Pendientes.Pedidos_Pendientes
import com.example.app_market.R
import com.example.app_market.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import storage.StoragePreferences

class DashBoardAdministrador : AppCompatActivity() {

    private lateinit var btnLogout: LinearLayout
    private lateinit var btnshowpedEntre: CardView
    private lateinit var btnshowpedPende: CardView

    private val preference = StoragePreferences.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dash_board_administrador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnLogout = findViewById(R.id.btcerrar)
        btnshowpedEntre = findViewById(R.id.card_view_entregados)
        btnshowpedPende = findViewById(R.id.card_view_pendiente)

        btnshowpedEntre.setOnClickListener {
            val intent = Intent(this@DashBoardAdministrador, Pedidos_Entregados::class.java)
            startActivity(intent)
        }


        btnshowpedPende.setOnClickListener {
            val intent = Intent(this@DashBoardAdministrador, Pedidos_Pendientes::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {

            val artDialogBuilder = AlertDialog.Builder(this@DashBoardAdministrador)
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
                    Toast.makeText(
                        this@DashBoardAdministrador,
                        "Clicked No",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            val alertDialogBox = artDialogBuilder.create()
            alertDialogBox.show()
        }


        lifecycleScope.launch(Dispatchers.IO) {
            preference.getCredentiales().collect {
                withContext(Dispatchers.Main) {
                    if (it.id == null) {
                        val intent = Intent(this@DashBoardAdministrador, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

    }
}