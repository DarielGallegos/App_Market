package client.services

import client.src.HEADERS
import model.email.ApiResponseEmail
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers

interface EmailService {
    @FormUrlEncoded
    @POST("/")
    fun sendEmail(@Field("titulo") titulo: String,
                  @Field("mensaje") mensaje: String,
                  @Field("remitente") remitente: String)
    : Call<ApiResponseEmail>
}