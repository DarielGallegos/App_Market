package com.example.app_market

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import service.impl.LocalizacionServiceAppImpl

class TrackingActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var usuario: String
    private lateinit var ubicacion: String
    private var idUsuario = 0
    private var numeroPedido = 0
    private lateinit var map: GoogleMap
    private lateinit var mMap: MapView
    private var dirCoordinates: LatLng? = null
    private lateinit var task : TaskLoadCoordenadas
    private lateinit var marker : Marker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_rastreo)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.colorPrimaryDark, theme)
        }

        usuario = intent.getStringExtra("usuario") ?: ""
        ubicacion = intent.getStringExtra("ubicacion") ?: ""
        idUsuario = intent.getIntExtra("idUsuario", 0)
        numeroPedido = intent.getIntExtra("numeroPedido", 0)

        val btntrack: Button = findViewById(R.id.btnTracking)
        findViewById<TextView>(R.id.txtnum_pedido).text = numeroPedido.toString()
        findViewById<TextView>(R.id.txtrepartidor).text = usuario
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btntrack.setOnClickListener {
            finish()
        }
        mMap = findViewById(R.id.mapView)
        mMap.onCreate(savedInstanceState)
        mMap.getMapAsync(this)
        setDir(ubicacion)
        setupMapIfReady()
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isCompassEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = true
        map.uiSettings.isZoomGesturesEnabled = true
        map.uiSettings.isScrollGesturesEnabled = true
        map.uiSettings.isTiltGesturesEnabled = true
        map.uiSettings.isRotateGesturesEnabled = true
        map.uiSettings.isMapToolbarEnabled = true
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

    override fun onDestroy() {
        super.onDestroy()
        task.stopTask()
    }
    private fun initMarkerRepartidor() : Marker{
        marker = map.addMarker(
            MarkerOptions()
                .position(LatLng(0.0,0.0))
                .title("Repartidor")
        )!!
        marker.tag = "Repartidor"
        return marker
    }

    private fun setupMapIfReady() {
        dirCoordinates?.let { coordinates ->
            if (::map.isInitialized) {
                val location = coordinates
                map.addMarker(MarkerOptions().position(location).title("Cliente"))
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 13f))
                task = TaskLoadCoordenadas(this, idUsuario, map, initMarkerRepartidor())
                task.startTask()
            }
        }
    }

}