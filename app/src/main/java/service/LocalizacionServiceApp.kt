package service

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

interface LocalizacionServiceApp {
    fun actualizarLocalizacion(idUsuario: Int, direccion: String)
    fun obtenerLocalizacion(idUsuario: Int, map: GoogleMap, marker: Marker)
}