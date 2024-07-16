package service.impl

import android.content.Context
import android.util.Log
import client.Client
import client.services.ClientServiceMethods
import client.services.EmailService
import com.google.gson.Gson
import controller.impl.RegisterClientControllerImpl
import model.common.ApiResponseBody
import model.dto.POST.ClientPOST
import model.email.ApiResponseEmail
import service.RegisterClientService

class RegisterClientServiceImpl(context: Context) : RegisterClientService {
    private val clientService = Client.ClientRetrofit.getService(ClientServiceMethods::class.java) as ClientServiceMethods
    private val controller = RegisterClientControllerImpl(context)
    override fun saveClient(e: ClientPOST) {
        clientService.saveClient(e).enqueue(object : retrofit2.Callback<ApiResponseBody> {
            override fun onResponse(call: retrofit2.Call<ApiResponseBody>, response: retrofit2.Response<ApiResponseBody>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        controller.saveClient(responseBody.status == "CREATED")
                    }else{
                        Log.d("Register Client", "Response es null")
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<ApiResponseBody>, t: Throwable) {
                Log.e("Error Client: ", t.message.toString())
            }
        })
    }

    override fun sendEmail(titulo: String, mensaje: String, remitente: String) {
        val clienteEmail = Client.ClientEmail.getService(EmailService::class.java) as EmailService
        clienteEmail.sendEmail(titulo, mensaje, remitente).enqueue(object : retrofit2.Callback<ApiResponseEmail> {
            override fun onResponse(call: retrofit2.Call<ApiResponseEmail>, response: retrofit2.Response<ApiResponseEmail>) {
                Log.d("Send Email", response.body().toString())
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        controller.sendEmail(responseBody.status == "OK")
                    }else{
                        Log.d("Send Email", "Response es null")
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<ApiResponseEmail>, t: Throwable) {
                Log.e("Error Email: ", t.message.toString())
            }
        })
    }
}