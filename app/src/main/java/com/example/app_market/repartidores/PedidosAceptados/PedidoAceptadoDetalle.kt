package com.example.app_market.repartidores.PedidosAceptados

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_market.R
import com.example.app_market.databinding.ActivityPedidoAceptadoDetalleBinding
import com.example.app_market.databinding.ActivityPedidosAceptadosBinding
import com.example.app_market.repartidores.ListaProductos.ListaProductos
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import model.dto.REQUEST.CabeceraPedidoDataContact
import service.impl.PedidoDisponibleServiceImpl
import utils.MailSender
import view.PedidoDisponibleView

class PedidoAceptadoDetalle : AppCompatActivity(), PedidoDisponibleView, OnMapReadyCallback {

    private lateinit var btnBack: ImageView
    private lateinit var btnVerProductos: Button
    private lateinit var btnFinish: Button
    private lateinit var btnCall: Button
    private lateinit var btnInitTravel: Button
    private lateinit var txtNombre: EditText
    private lateinit var txtTelefono: EditText
    private lateinit var txtDireccion: EditText
    private lateinit var txtTotal: EditText
    private lateinit var map: MapView
    private lateinit var mMap: GoogleMap
    private var dirCoordinates: LatLng? = null
    private var numPedido = 0
    private val service = PedidoDisponibleServiceImpl(this)

    private var email = ""
    private val mail = MailSender()

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pedido_aceptado_detalle)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.colorPrimaryDark, theme)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        numPedido = intent.getIntExtra("NumeroPedido", 0)

        btnCall = findViewById(R.id.btnCall)
        btnFinish = findViewById(R.id.btnFinish)
        btnVerProductos = findViewById(R.id.btnVerProductos)
        btnInitTravel = findViewById(R.id.btnInitTravel)
        btnBack = findViewById(R.id.imgBack)
        txtNombre = findViewById(R.id.txtNombres)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtTotal = findViewById(R.id.txtTotal)
        map = findViewById(R.id.mapViewAccept)

        service.getPedidoDisponible(numPedido)

       btnBack.setOnClickListener{
            val intent = Intent(this@PedidoAceptadoDetalle, Pedidos_Aceptados::class.java)
            startActivity(intent)
        }

        btnVerProductos.setOnClickListener {
            val intent = Intent(this@PedidoAceptadoDetalle, ListaProductos::class.java)
            intent.putExtra("NumeroPedido", numPedido)
            startActivity(intent)
        }

        btnFinish.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("¿Da por finalizado el pedido?")
                .setPositiveButton("Si") { _, _ ->
                    service.finishPedido(numPedido)
                }
                .setNegativeButton("No") { _, _ -> }
                .show()
        }

        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:+504${txtTelefono.text}")
            startActivity(intent)
        }

        btnInitTravel.setOnClickListener {
            dirCoordinates?.let { coordinates ->
                AlertDialog.Builder(this)
                    .setTitle("¿Desea Iniciar el Viaje?")
                    .setPositiveButton("Si") { _, _ ->
                        val locateIntentUri = Uri.parse("google.navigation:q=${coordinates.latitude},${coordinates.longitude}")
                        val travelIntent = Intent(Intent.ACTION_VIEW, locateIntentUri)
                        travelIntent.setPackage("com.google.android.apps.maps")
                        startActivity(travelIntent)
                    }
                    .setNegativeButton("No") { _, _ -> }
                    .show()
            }
        }

        map.onCreate(savedInstanceState)
        map.getMapAsync(this)

        btnBack.setOnClickListener{
            val intent = Intent(this@PedidoAceptadoDetalle, Pedidos_Aceptados::class.java)
            startActivity(intent)
        }
    }

    override fun initView(e: CabeceraPedidoDataContact) {
        txtNombre.setText(e.cliente)
        txtTelefono.setText(e.telefono)
        txtDireccion.setText(e.ubicacion)
        txtTotal.setText(e.total.toString())
        email = e.correo
        setDir(e.ubicacion)
        setupMapIfReady()
    }

    override fun acceptPedido(msg: String, status: String) {
    }

    override fun finishPedido(msg: String) {
        AlertDialog.Builder(this)
            .setTitle("Pedido Finalizado")
            .setMessage(msg)
            .setPositiveButton("Aceptar") { _, _ ->
                finish()
            }
            .show()

        val subject = "Entrega de Pedido - Supermercado El Economico"
        val body = """
<html>
<head>
        <style>
            body {
                font-family: Arial, sans-serif;
                color: #000000; 
            }
            p {
                margin: 0 0 10px 0;
            }
        </style>
    </head>
    <body>
        <p>Estimado/a cliente,</p>
        <p>Nos complace informarle que su pedido ha sido entregado con éxito y está ahora finalizado.</p>
        <p>Si tiene alguna pregunta o necesita asistencia adicional, no dude en contactarnos.</p>
        <p>Gracias por su compra.</p>
        <p>Atentamente,<br>El equipo de Supermercado El Económico</p>
    </body>
</html>
""".trimIndent()
        mail.sendEmail(email, subject, body)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true
        mMap.uiSettings.isScrollGesturesEnabled = true
        mMap.uiSettings.isTiltGesturesEnabled = true
        mMap.uiSettings.isRotateGesturesEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true
        setupMapIfReady()
    }

    private fun setDir(location: String) {
        val coords = location.split(",")
        if (coords.size == 2) {
            val lat = coords[0].toDoubleOrNull()
            val lng = coords[1].toDoubleOrNull()
            if (lat != null && lng != null) {
                dirCoordinates = LatLng(lat, lng)
            }
        }
    }

    private fun setupMapIfReady() {
        dirCoordinates?.let { coordinates ->
            if (::mMap.isInitialized) {
                val location = coordinates
                mMap.addMarker(MarkerOptions().position(location).title("Cliente"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
            }
        }
    }
}