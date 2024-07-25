package service.impl

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.EditText
import client.Client
import client.services.ClientServiceMethods
import client.services.EmailService
import com.google.gson.Gson
import controller.impl.RegisterClientControllerImpl
import model.common.ApiResponseBody
import model.dto.POST.ClientPOST
import model.dto.REQUEST.ClientData
import model.email.ApiResponseEmail
import service.RegisterClientService

class RegisterClientServiceImpl(context: Context) : RegisterClientService {
    private val clientService = Client.ClientRetrofit.getService(ClientServiceMethods::class.java) as ClientServiceMethods
    private val controller = RegisterClientControllerImpl(context)
    private val context = context

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

    override fun updateClient(clientIdToUpdate: Int, clientPOST: Int) {
        TODO("Not yet implemented")
    }


    override fun saveClientValidation(e: ClientPOST, code: Int?) {
        sendEmail("Registro de Cliente", "Codigo de Validacion: $code", e.correo)
        val validationCode = AlertDialog.Builder(this.context)
        validationCode.setTitle("Código de validación")
        validationCode.setMessage("Se ha enviado un código de validación a su correo")
        val input = EditText(this.context)
        input.hint = "Código de validación"
        input.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        validationCode.setView(input)
        validationCode.setPositiveButton("Validar") { dialog, _ ->
            if (input.text.toString() == code.toString()) {
                saveClient(e)
            } else {
                dialog.dismiss()
                val alert = AlertDialog.Builder(this.context)
                alert.setTitle("Código de validación")
                alert.setMessage("El código ingresado es incorrecto")
                alert.setPositiveButton("Aceptar") { dialog, _ ->
                    dialog.dismiss()
                }
                alert.show()
            }
        }
        validationCode.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }
        validationCode.show()
    }

    override fun sendEmail(titulo: String, mensaje: String, remitente: String) {
        val clienteEmail = Client.ClientEmail.getService(EmailService::class.java) as EmailService
        clienteEmail.sendEmail(titulo, mensaje, remitente).enqueue(object : retrofit2.Callback<ApiResponseEmail> {
            override fun onResponse(call: retrofit2.Call<ApiResponseEmail>, response: retrofit2.Response<ApiResponseEmail>) {
                Log.d("Send Email", response.body().toString())
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                    }else{
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<ApiResponseEmail>, t: Throwable) {
            }
        })
    }



        override fun loadClient(id: Int) {
            clientService.loadClient(id).enqueue(object : retrofit2.Callback<ApiResponseBody> {
                override fun onResponse(call: retrofit2.Call<ApiResponseBody>, response: retrofit2.Response<ApiResponseBody>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            val gson = Gson()
                            val list = gson.fromJson(gson.toJson(responseBody.data.content), Array<ClientData>::class.java).toList()
                            controller.loadClient(list)
                        } else {
                            Log.d("Load Client", "Response es null")
                        }
                    } else {
                        Log.e("Load Client", "Error en la respuesta")
                    }
                }

                override fun onFailure(call: retrofit2.Call<ApiResponseBody>, t: Throwable) {
                    Log.e("Error Load Client: ", t.message.toString())
                }
            })
        }
    }
