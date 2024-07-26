package com.example.app_market.car_market

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.app_market.R
import com.example.app_market.databinding.ActivityDetallesProductosFinancierosBinding
import com.example.app_market.databinding.ActivityProductosCarMarketBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import storage.StoragePreferences

class DetallesProductosFinancieros : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var preferences = StoragePreferences.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_productos_financieros)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val subtotal = intent.getDoubleExtra("SUBTOTAL", 0.0)
        val impuesto = intent.getDoubleExtra("IMPUESTO", 0.0)
        val envio = intent.getDoubleExtra("ENVIO", 0.0)
        val total = intent.getDoubleExtra("TOTAL", 0.0)

        val txtSubtotal = findViewById<EditText>(R.id.txtsubtotal)
        val txtImpuesto = findViewById<EditText>(R.id.txtimpuesto)
        val txtEnvio = findViewById<EditText>(R.id.txtenvio)
        val txtTotal = findViewById<EditText>(R.id.txttotal)
        val txtCliente = findViewById<EditText>(R.id.txtcliente)
        val txtTelefono = findViewById<EditText>(R.id.txttelefono)

        lifecycleScope.launch(Dispatchers.IO) {
            preferences.getCredentiales().collect {
                withContext(Dispatchers.Main) {
                    if (it.id != null) {
                        txtCliente.setText(it.nombre)
                        txtTelefono.setText(it.telefono)
                    }
                }
            }
        }

        txtSubtotal.setText(subtotal.toString())
        txtImpuesto.setText(impuesto.toString())
        txtEnvio.setText(envio.toString())
        txtTotal.setText(total.toString())
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            enableMyLocation()
        }
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.isMyLocationEnabled = true
            getLastLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val latLng = LatLng(it.latitude, it.longitude)
                googleMap.addMarker(MarkerOptions().position(latLng).title("¡ESTAS AQUI!"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation()
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}