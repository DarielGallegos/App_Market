package service.impl

import android.content.Context
import client.Client
import client.services.RecoveryService
import controller.impl.ResetPasswordControllerImpl
import model.common.ApiResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.ResetPasswordService

class ResetPasswordServiceImpl(context: Context): ResetPasswordService {
    val controller = ResetPasswordControllerImpl(context)
    val client = Client.ClientRetrofit.getService(RecoveryService::class.java) as RecoveryService
    override fun resetPassword(email: String, passwd: String) {
        client.resetPassword(email, passwd).enqueue(
            object : Callback<ApiResponseBody> {
                override fun onResponse(call: Call<ApiResponseBody>, response: Response<ApiResponseBody>) {
                    if(response.isSuccessful){
                        controller.resetPasswd(response.body()?.status == "OK")
                    }
                }

                override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                    controller.resetPasswd(false)
                }
            }
        )
    }
}