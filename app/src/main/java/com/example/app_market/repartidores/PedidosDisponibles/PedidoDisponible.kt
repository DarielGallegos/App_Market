package com.example.app_market.repartidores.PedidosDisponibles

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.app_market.R
import com.example.app_market.repartidores.ListaProductos.ListaProductos
import com.example.app_market.repartidores.PedidosAceptados.Pedidos_Aceptados
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.dto.REQUEST.CabeceraPedidoDataContact
import service.impl.PedidoDisponibleServiceImpl
import storage.StoragePreferences
import utils.MailSender
import view.PedidoDisponibleView

class PedidoDisponible : AppCompatActivity(), PedidoDisponibleView, OnMapReadyCallback, OnMapLoadedCallback {

    private lateinit var btnBack: ImageView
    private lateinit var btnVerProductos: Button
    private lateinit var btnAccept: Button
    private lateinit var txtNombre: EditText
    private lateinit var txtTelefono: EditText
    private lateinit var txtDireccion: EditText
    private lateinit var txtTotal: EditText
    private lateinit var map: MapView
    private lateinit var mMap : GoogleMap
    private val service = PedidoDisponibleServiceImpl(this)
    private var numPedido = 0
    private var dirCoordinates: LatLng? = null
    private val storage = StoragePreferences.getInstance(this)
    private val mail = MailSender()
    private var email = ""
    private var user = ""
    private var phone = ""
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pedido_disponible_detalle)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.colorPrimaryDark, theme)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        numPedido = intent.getIntExtra("NumeroPedido", 0)
        btnVerProductos = findViewById(R.id.btnVerProductos)
        btnAccept = findViewById(R.id.btnAccept)
        txtNombre = findViewById(R.id.txtNombres)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtTotal = findViewById(R.id.txtTotal)
        map = findViewById(R.id.mapViewAccept)
        service.getPedidoDisponible(numPedido)
        btnBack = findViewById(R.id.imgBack)

        btnBack.setOnClickListener{
            val intent = Intent(this@PedidoDisponible, Pedidos_Disponibles::class.java)
            startActivity(intent)
        }

        btnVerProductos.setOnClickListener{
            val intent = Intent(this@PedidoDisponible, ListaProductos::class.java)
            intent.putExtra("NumeroPedido",numPedido)
            startActivity(intent)
        }
        btnAccept.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO) {
                storage.getCredentiales().collect {
                    if(it.id != null){
                        user = it.nombre.toString()
                        phone = it.telefono.toString()
                        service.acceptPedido(numPedido, it.id)
                    }
                }
            }
        }
        map.onCreate(savedInstanceState)
        map.getMapAsync(this)
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
        AlertDialog.Builder(this)
        .setTitle(status)
        .setMessage(msg)
        .setPositiveButton("Aceptar") { _, _ ->
            finish()
        }
            .show()
        if(status != "Pedido Rechazado"){
            mail.sendEmail(email, "Supermerrcado El Económico", "Su pedido ha sido aceptado por nuestro repartidor: $user \n Su número de pedido es: $numPedido \n Puede contactar a nuestro repartidor mediante: +504 $phone")
        }
    }

    override fun finishPedido(msg: String) {
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        p0.mapType = GoogleMap.MAP_TYPE_NORMAL
        p0.uiSettings.isZoomControlsEnabled = true
        p0.uiSettings.isCompassEnabled = true
        p0.uiSettings.isMyLocationButtonEnabled = true
        p0.uiSettings.isZoomGesturesEnabled = true
        p0.uiSettings.isScrollGesturesEnabled = true
        p0.uiSettings.isTiltGesturesEnabled = true
        p0.uiSettings.isRotateGesturesEnabled = true
        p0.uiSettings.isMapToolbarEnabled = true
        setupMapIfReady()
    }

    private fun setDir(e: String) {
        val coords = e.split(",")
        if(coords.size == 2){
            val lat = coords[0].toDoubleOrNull()
            val lng = coords[1].toDoubleOrNull()
            if(lat != null && lng != null){
                dirCoordinates = LatLng(lat, lng)
            }
        }
    }

    private fun setupMapIfReady(){
        dirCoordinates?.let{ it ->
            if(::mMap.isInitialized){
                val location = it
                mMap.addMarker(MarkerOptions().position(location).title("Cliente"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
            }
        }
    }

    override fun onMapLoaded() {
    }
}