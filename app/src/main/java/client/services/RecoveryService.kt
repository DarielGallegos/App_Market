package client.services

import model.common.ApiResponseBody
import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Query

interface RecoveryService {
    @PUT("api/v1/security/")
    fun resetPassword(@Query("email") email:String, @Query("passwd") passwd:String ): Call<ApiResponseBody>
}