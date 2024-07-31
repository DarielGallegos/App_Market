package com.example.app_market

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import com.google.android.gms.maps.GoogleMap
import service.impl.LocalizacionServiceAppImpl

class TaskLoadCoordenadas(context: Context, private val id: Int, private val map: GoogleMap): HandlerThread("TaskLoadCoordenadas") {
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
            service.obtenerLocalizacion(id, map)
            handler.postDelayed(this, 60000)
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