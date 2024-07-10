package client.services

import model.common.ApiResponseBody
import model.dto.POST.ClientPOST
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ClientServiceMethods {
    @POST("api/v1/Clientes/")
    fun saveClient(@Body e: ClientPOST): Call<ApiResponseBody>
}