package client.services

import model.common.ApiResponseBody
import model.dto.POST.ClientPOST
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ClientServiceMethods {
    @POST("api/v1/Clientes/")
    fun saveClient(@Body e: ClientPOST): Call<ApiResponseBody>

    @PUT("api/v1/Clientes/{id}")
    fun updateClient(@Path("id") id: Int, @Body client: Any): Call<ApiResponseBody>

    @GET("api/v1/Clientes/{id}")
    fun loadClient(@Path("id") id: Int): Call<ApiResponseBody>




}
