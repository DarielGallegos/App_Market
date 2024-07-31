package com.example.app_market.repartidores

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import service.impl.LocalizacionServiceAppImpl

class TaskSaveCoordenadas(private val context: Context, private val id: Int) : HandlerThread("TaskSaveCoordenadas") {

    private lateinit var handler: Handler
    private val service = LocalizacionServiceAppImpl(context)
    private lateinit var locClient : FusedLocationProviderClient
    private lateinit var locRequest : LocationRequest
    private lateinit var locCallback : LocationCallback

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
        locClient = LocationServices.getFusedLocationProviderClient(context)
        locRequest = LocationRequest.create().apply{
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locCallback = object: LocationCallback(){
            override fun onLocationResult(locationResult: com.google.android.gms.location.LocationResult) {
                super.onLocationResult(locationResult)
                for(locationResult in locationResult.locations){
                    updateLocation(locationResult)
                }
            }
        }
        locClient.requestLocationUpdates(locRequest, locCallback, Looper.getMainLooper())
    }

    private fun updateLocation(location: Location){
        val latLng = LatLng(location.latitude, location.longitude)
        val dir = "${latLng.latitude},${latLng.longitude}"
        service.actualizarLocalizacion(id, dir)
    }

    fun startTask() {
        start()
    }

    fun stopTask() {
        handler.removeCallbacks(task)
        quitSafely()
    }
}
