package client.services

import model.common.ApiResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface LocalizacionService {
    @PUT("api/v1/repartidores/dir/{idUsuario}")
    fun actualizarLocalizacion(@Path("idUsuario") idUsuario: Int, @Query("direccion") direccion: String): Call<ApiResponseBody>

    @GET("api/v1/repartidores/dir/{idUsuario}")
    fun obtenerLocalizacion(@Path("idUsuario") idUsuario: Int): Call<ApiResponseBody>
}