package com.example.app_market.login.recoveryPassword

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_market.R
import service.impl.ResetPasswordValidationServiceImpl
import view.validations.ValidationResetPassword

class ResetPasswordEmailActivity : AppCompatActivity() {
    private val service = ResetPasswordValidationServiceImpl(this)
    private lateinit var emailField: EditText
    private lateinit var btnAction: Button
    private val validator = ValidationResetPassword()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reset_password_email)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        emailField = findViewById(R.id.txtEmailFormRecoveryPassword)
        btnAction = findViewById(R.id.btnRequestCodeValidate)
        btnAction.setOnClickListener {
            if(!validator.validateFields(emailField.text.toString())){
                emailField.error = "Campo obrigatório"
                return@setOnClickListener
            }
            service.sendMail(emailField.text.toString())
        }
    }
}