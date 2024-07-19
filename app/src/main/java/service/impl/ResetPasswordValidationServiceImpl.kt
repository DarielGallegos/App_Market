package service.impl

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.widget.EditText
import client.Client
import client.services.EmailService
import com.example.app_market.login.recoveryPassword.ResetPasswordActivity
import model.email.ApiResponseEmail
import service.ResetPasswordValidationService
import view.validations.ValidationResetPassword
import java.util.Random

class ResetPasswordValidationServiceImpl(context: Context) : ResetPasswordValidationService {
    private val context: Context = context
    private val client = Client.ClientEmail.getService(EmailService::class.java) as EmailService
    override fun sendMail(email: String) {
        val code = Random().nextInt(999999)
        sendMailCode(email, code)
    }

    private fun sendMailCode(E: String, code: Int){
        client.sendEmail("Recuperación de Cuenta", "Código de Validación: $code", E).enqueue(object : retrofit2.Callback<ApiResponseEmail> {
            override fun onResponse(call: retrofit2.Call<ApiResponseEmail>, response: retrofit2.Response<ApiResponseEmail>) {
                if (response.isSuccessful) {
                    validateCode(code, E)
                }
            }

            override fun onFailure(call: retrofit2.Call<ApiResponseEmail>, t: Throwable) {
            }
        })
    }

    private fun validateCode(code: Int, email: String){
        val field = fieldValidate()
        val modal = AlertDialog.Builder(this.context)
            .setTitle("Código de Validación")
            .setMessage("Ingrese el código de validación")
            .setView(field)
            .setPositiveButton("Aceptar") { _, _ ->
                if (field.text.toString() == code.toString()) {
                    val intent = Intent(context.applicationContext, ResetPasswordActivity::class.java)
                    intent.putExtra("email", email)
                    context.startActivity(intent)
                    (context as Activity).finish()
                } else {
                    val alert = AlertDialog.Builder(this.context)
                        .setTitle("Código de validación")
                        .setMessage("El código ingresado es incorrecto")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                    alert.show()
                }
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
        modal.show()
    }

    private fun fieldValidate(): EditText {
        return EditText(this.context).apply {
            hint = "Código de validación"
            textAlignment= android.view.View.TEXT_ALIGNMENT_CENTER
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
        }
    }
}