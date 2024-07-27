package com.example.app_market.repartidores.PedidosDisponibles

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.app_market.R
import com.example.app_market.repartidores.ListaProductos.ListaProductos
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
import view.PedidoDisponibleView

class PedidoDisponible : AppCompatActivity(), PedidoDisponibleView, OnMapReadyCallback, OnMapLoadedCallback {

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
    private var dirText = ""
    private val storage = StoragePreferences.getInstance(this)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pedido_disponible_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dirText = ""
        numPedido = intent.getIntExtra("NumeroPedido", 0)
        btnVerProductos = findViewById(R.id.btnVerProductos)
        btnAccept = findViewById(R.id.btnAccept)
        txtNombre = findViewById(R.id.txtNombres)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtTotal = findViewById(R.id.txtTotal)
        map = findViewById(R.id.mapViewAccept)
        service.getPedidoDisponible(numPedido)

        btnVerProductos.setOnClickListener{
            val intent = Intent(this@PedidoDisponible, ListaProductos::class.java)
            intent.putExtra("NumeroPedido",numPedido)
            startActivity(intent)
        }
        btnAccept.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO) {
                storage.getCredentiales().collect {
                    if(it.id != null){
                        service.acceptPedido(numPedido, it.id)
                    }
                }
            }
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
        setDir(e.ubicacion)
    }

    override fun acceptPedido(msg: String, status: String) {
        AlertDialog.Builder(this)
        .setTitle(status)
        .setMessage(msg)
        .setPositiveButton("Aceptar") { _, _ ->
            finish()
        }
            .show()
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
        if(!getDir().isEmpty()){
            var direccion = getDir().split(",")
            var latitud = direccion[0].toDouble()
            var longitud = direccion[1].toDouble()
            if(mMap != null){
                val location = LatLng(latitud, longitud)
                mMap.addMarker(MarkerOptions().position(location).title("Marcador"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
            }
        }
    }

    private fun setDir(e : String) { this.dirText = e }
    private fun getDir() : String {return this.dirText}
    override fun onMapLoaded() {
    }
}