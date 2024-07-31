package service.impl

import android.content.Context
import android.widget.Toast
import client.Client
import client.services.LocalizacionService
import com.google.android.gms.maps.GoogleMap
import com.google.gson.Gson
import model.common.ApiResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.LocalizacionServiceApp

class LocalizacionServiceAppImpl(private val context: Context) : LocalizacionServiceApp {
private val client = Client.ClientRetrofit.getService(LocalizacionService::class.java) as LocalizacionService
    override fun actualizarLocalizacion(idUsuario: Int, direccion: String) {
        client.actualizarLocalizacion(idUsuario, direccion).enqueue(object : Callback<ApiResponseBody> {
            override fun onResponse(call: Call<ApiResponseBody>, response: Response<ApiResponseBody>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun obtenerLocalizacion(idUsuario: Int, map: GoogleMap) {
        client.obtenerLocalizacion(idUsuario).enqueue(object : Callback<ApiResponseBody> {
            override fun onResponse(call: Call<ApiResponseBody>, response: Response<ApiResponseBody>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val gson = Gson()
                        val ubicacion = gson.fromJson(responseBody.data.content.toString(), Array<String>::class.java)

                        Toast.makeText(context, "${ubicacion[0]} + ${ubicacion[1]}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

}