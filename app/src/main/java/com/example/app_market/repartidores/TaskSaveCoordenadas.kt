package com.example.app_market.repartidores

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Handler
import android.os.HandlerThread
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import service.impl.LocalizacionServiceAppImpl

class TaskSaveCoordenadas(private val context: Context, private val id: Int) : HandlerThread("TaskSaveCoordenadas") {

    private lateinit var handler: Handler
    private val service = LocalizacionServiceAppImpl(context)

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        handler = Handler(looper)
        handler.post(task)
    }

    private val task = object : Runnable {
        override fun run() {
            getLastLocation()
            handler.postDelayed(this, 10000)
        }
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as android.app.Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            return
        }
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val latLng = LatLng(it.latitude, it.longitude)
                val dir = "${latLng.latitude},${latLng.longitude}"
                Toast.makeText(context, dir, Toast.LENGTH_SHORT).show()
                service.actualizarLocalizacion(id, dir)
            }
        }
    }

    fun startTask() {
        start()
    }

    fun stopTask() {
        handler.removeCallbacks(task)
        quitSafely()
    }
}
