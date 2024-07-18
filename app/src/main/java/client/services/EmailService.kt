package client.services

import model.email.ApiResponseEmail
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

interface EmailService {
    @FormUrlEncoded
    @POST("/")
    fun sendEmail(@Field("titulo") titulo: String,
                  @Field("mensaje") mensaje: String,
                  @Field("remitente") remitente: String)
    : Call<ApiResponseEmail>
}