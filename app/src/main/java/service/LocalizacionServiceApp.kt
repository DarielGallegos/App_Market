package service

import com.google.android.gms.maps.GoogleMap

interface LocalizacionServiceApp {
    fun actualizarLocalizacion(idUsuario: Int, direccion: String)
    fun obtenerLocalizacion(idUsuario: Int, map: GoogleMap)
}