package client.services

import model.common.ApiResponseBody
import model.dto.POST.CredentialsPOST
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginServiceMethods {
    @POST("api/v1/security/")
    fun login(@Body e: CredentialsPOST): Call<ApiResponseBody>

}