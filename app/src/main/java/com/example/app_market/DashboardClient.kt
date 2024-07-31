package com.example.app_market




import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.app_market.login.LoginActivity
import com.example.app_market.login.formRegister.FormRegisterClientActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import storage.StoragePreferences

class DashboardClient : AppCompatActivity() {
    private lateinit var btnShowPedidos: CardView
    private lateinit var btnNuevoPedido: CardView
    private lateinit var btnLogout: LinearLayout
    private lateinit var btnActualizar: LinearLayout
    private lateinit var btnPedidosFinalizados:CardView

    private val preference = StoragePreferences.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard_client)

        btnLogout = findViewById(R.id.btcerrar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnShowPedidos = findViewById(R.id.card_view_pedido)
        btnShowPedidos.setOnClickListener {
            val intent = Intent(this, ListPedidoActivity::class.java)
            startActivity(intent)
        }
        btnNuevoPedido = findViewById(R.id.card_view_nuevo_pedido)
        btnActualizar = findViewById(R.id.btactualizar)
        btnPedidosFinalizados = findViewById(R.id.card_view_pedidos_finalizados)

        btnNuevoPedido.setOnClickListener {
            val intent = Intent(this, CatalogoProductosActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            val artDialogBuilder = AlertDialog.Builder(this)
            artDialogBuilder.setMessage("¿Desea cerrar sesión?")
                .setCancelable(true)
                .setPositiveButton("Sí") { _, _ ->
                    lifecycleScope.launch(Dispatchers.IO) {
                        preference.deleteCredentials()
                        withContext(Dispatchers.Main) {
                            finish()
                        }
                    }
                }
                .setNegativeButton("No") { _, _ ->
                }
            val alertDialogBox = artDialogBuilder.create()
            alertDialogBox.show()
        }

        lifecycleScope.launch(Dispatchers.IO) {
            preference.getCredentiales().collect {
                withContext(Dispatchers.Main) {
                    if (it.id == null) {
                        val intent = Intent(this@DashboardClient, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        initChannelPrivateClient(it.id)
                    }
                }
            }
        }

        btnNuevoPedido.setOnClickListener {
            val intent = Intent(this, CatalogoProductosActivity::class.java)
            startActivity(intent)
        }

        btnActualizar.setOnClickListener {
            val intent = Intent(this, FormActualizarRegisterClientActivity::class.java)
            startActivity(intent)
        }

        btnPedidosFinalizados.setOnClickListener{
            val intent = Intent(this,ListaPendienteValoracionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initChannelPrivateClient(id: Int){
        //Crea canal personalizado de notificaciones
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelId = "channel_private_client_${id}"
            val name = "Canal de notificaciones"
            val descriptionText = "Canal de notificaciones privadas"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
