package com.example.app_market.repartidores.PedidosAceptados

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_market.R
import com.example.app_market.repartidores.ListaProductos.ListaProductos
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import model.dto.REQUEST.CabeceraPedidoDataContact
import service.impl.PedidoDisponibleServiceImpl
import view.PedidoDisponibleView

class PedidoAceptadoDetalle : AppCompatActivity(), PedidoDisponibleView, OnMapReadyCallback {

    private lateinit var btnVerProductos: Button
    private lateinit var btnFinish: Button
    private lateinit var btnCall : Button
    private lateinit var btnInitTravel : Button
    private lateinit var txtNombre : EditText
    private lateinit var txtTelefono : EditText
    private lateinit var txtDireccion : EditText
    private lateinit var txtTotal : EditText
    private lateinit var map: MapView
    private lateinit var mMap: GoogleMap
    private var numPedido = 0
    private val service = PedidoDisponibleServiceImpl(this)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pedido_aceptado_detalle)
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
        txtNombre = findViewById(R.id.txtNombres)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtTotal = findViewById(R.id.txtTotal)
        map = findViewById(R.id.mapViewAccept)

        this.service.getPedidoDisponible(numPedido)
        btnVerProductos.setOnClickListener{
            val intent = Intent(this@PedidoAceptadoDetalle, ListaProductos::class.java)
            intent.putExtra("NumeroPedido", numPedido)
            startActivity(intent)
        }
        btnFinish.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("¿Da por finalizado el pedido?")
                .setPositiveButton("Si") { _, _ ->
                    this.service.finishPedido(numPedido)
                }
                .setNegativeButton("No") { _, _ ->
                }
                .show()
        }
        btnCall.setOnClickListener{
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = android.net.Uri.parse("tel:+504${txtTelefono.text}")
            startActivity(intent)
        }
        btnInitTravel.setOnClickListener{
            val direccion = txtDireccion.text.split(",")
            val latitud = direccion[0]
            val longitud = direccion[1]
            AlertDialog.Builder(this)
                .setTitle("¿Desea Iniciar el Viaje?")
                .setPositiveButton("Si") { _, _ ->
                    val locateIntentUri =
                        Uri.parse("google.navigation:q=" + latitud + "," + longitud)
                        val travelIntent = Intent(Intent.ACTION_VIEW, locateIntentUri)
                        travelIntent.setPackage("com.google.android.apps.maps")
                        startActivity(travelIntent)
                }
                .setNegativeButton("No") { _, _ ->}
                .show()
        }
        map.onCreate(savedInstanceState)
        map.getMapAsync(this)
        map.onResume()
    }

    override fun initView(e: CabeceraPedidoDataContact) {
        txtNombre.setText(e.cliente)
        txtTelefono.setText(e.telefono)
        txtDireccion.setText(e.ubicacion)
        txtTotal.setText(e.total.toString())

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
        var direccion = txtDireccion.text.split(",")
        var latitud = direccion[0].toDouble()
        var longitud = direccion[1].toDouble()
        if(mMap != null){
            val location = LatLng(latitud, longitud)
            mMap.addMarker(MarkerOptions().position(location).title("Marcador"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        }
    }
}