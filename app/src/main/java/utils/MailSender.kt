package utils

import client.Client
import client.services.EmailService
import model.email.ApiResponseEmail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MailSender {
    private val client = Client.ClientEmail.getService(EmailService::class.java) as EmailService
    fun sendEmail(to: String, subject: String, body: String) {
        client.sendEmail(subject, body, to).enqueue(object: Callback<ApiResponseEmail>{
            override fun onFailure(call: Call<ApiResponseEmail>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ApiResponseEmail>,
                response: Response<ApiResponseEmail>
            ) {
            }

        })
    }
}