package com.example.app_market.login.recoveryPassword

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_market.R
import service.impl.ResetPasswordServiceImpl
import view.ResetPasswordView
import view.validations.ValidationResetPassword

class ResetPasswordActivity : AppCompatActivity(), ResetPasswordView {

    private val service = ResetPasswordServiceImpl(this)
    private val validation = ValidationResetPassword()
    private lateinit var passwdField : EditText
    private lateinit var passwdConfirmField : EditText
    private lateinit var btnRequest : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reset_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val email:String = intent.getStringExtra("email").toString()
        passwdField = findViewById(R.id.txtPasswordRecovery)
        passwdConfirmField = findViewById(R.id.txtPasswordRecoveryConfirm)
        btnRequest = findViewById(R.id.btnUpdatePassword)

        btnRequest.setOnClickListener {
            if(validation.validateFields(passwdField.text.toString()) && validation.validateFields(passwdConfirmField.text.toString())){
               if(validation.validateEqualFields(passwdField.text.toString(), passwdConfirmField.text.toString())) {
                   service.resetPassword(email, passwdConfirmField.text.toString())
                   finish()
                   return@setOnClickListener
               }
            }
            AlertDialog.Builder(this)
                .setTitle("Reinicio de Contraseña")
                .setMessage("Las contraseñas no coinciden")
                .setPositiveButton("Aceptar") { _, _ -> }
                .show()
        }
    }

    override fun getStatus(status: Boolean) {
        AlertDialog.Builder(this)
            .setTitle("Reinicio de Contraseña")
            .setMessage(
                if (status) "Se ha reiniciado la contraseña correctamente"
                else "No se ha podido reiniciar la contraseña"
            ).setPositiveButton("Aceptar") { _, _ -> }
            .show()
    }
}