package com.example.app_market.Administracion.Pendientes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_market.databinding.ActivityDetallesPendientesBinding
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

class DetallesPedidos : AppCompatActivity(), PedidoDisponibleView, OnMapReadyCallback {

    private lateinit var binding: ActivityDetallesPendientesBinding

    private lateinit var map: MapView
    private lateinit var mMap: GoogleMap
    private var dirCoordinates: LatLng? = null

    private val service = PedidoDisponibleServiceImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesPendientesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        service.getPedidoDisponible(intent.getIntExtra("NumeroPedido", 0))
        binding.btnVerProductos.setOnClickListener{
            val intentProd = Intent(this, ListaProductos::class.java)
            intentProd.putExtra("NumeroPedido", intent.getIntExtra("NumeroPedido", 0))
            startActivity(intentProd)
        }
        map = binding.mapView
        binding.imgBack.setOnClickListener {
            finish()
        }
        map.onCreate(savedInstanceState)
        map.getMapAsync(this)
    }

    override fun initView(e: CabeceraPedidoDataContact) {
        binding.txtNombres.setText(e.cliente)
        binding.txtTelefono.setText(e.telefono)
        binding.txtDireccion.setText(e.ubicacion)
        binding.txtTotalAdm.setText(e.total.toString())
        setDir(e.ubicacion)
        setupMapIfReady()
    }

    override fun acceptPedido(msg: String, status: String) {
    }

    override fun finishPedido(msg: String) {
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
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