package com.example.app_market.repartidores

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Handler
import android.os.HandlerThread
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import service.impl.LocalizacionServiceAppImpl

class TaskSaveCoordenadas(context: Context, private val id: Int, private val fusedLocationClient: FusedLocationProviderClient) : HandlerThread("TaskSaveCoordenadas") {

    private lateinit var handler: Handler
    private val service = LocalizacionServiceAppImpl(context)
    override fun onLooperPrepared() {
        super.onLooperPrepared()
        handler = Handler(looper)
        handler.postDelayed(task, 1000)
    }

    private val task = object : Runnable {
        override fun run() {
            // Do something
            getLastLocation()
            handler.postDelayed(this, 60000)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val latLng = LatLng(it.latitude, it.longitude)
                val dir = latLng.latitude.toString() + "," + latLng.longitude.toString()
                service.actualizarLocalizacion(id, dir)
            }
        }
    }
    fun startTask() {
        start()
    }

    fun stopTask() {
        handler.removeCallbacks(task)
        quit()
    }
}