package com.example.app_market.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.app_market.DashboardClient
import com.example.app_market.login.formRegister.FormRegisterClientActivity
import com.example.app_market.R
import com.example.app_market.login.recoveryPassword.ResetPasswordEmailActivity
import com.example.app_market.repartidores.DashBoardRepartidoresActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.common.Producto
import model.dto.POST.CredentialsPOST
import service.impl.LoginServiceImpl
import storage.DataStoreCarMarket
import storage.StoragePreferences
import view.loginView

class LoginActivity : loginView,  AppCompatActivity(){

    private var preferences = StoragePreferences.getInstance(this)
    private lateinit var loginButton: Button
    private lateinit var txtuser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var register: TextView
    private lateinit var forgot: TextView
    private lateinit var chkClient: CheckBox
    private val service = LoginServiceImpl(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        txtuser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)

        register = findViewById(R.id.register)
        forgot = findViewById(R.id.forgot)

        chkClient = findViewById(R.id.chkClientLogin)

        register.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, FormRegisterClientActivity::class.java)
            startActivity(intent)
        })
        forgot.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, ResetPasswordEmailActivity::class.java)
            startActivity(intent)
        })

        lifecycleScope.launch(Dispatchers.IO) {
            preferences.getCredentiales().collect {
                withContext(Dispatchers.Main) {
                    if(it.id != null){
                        start(it.empleado?:false)
                    }
                }
            }
        }

        loginButton = findViewById(R.id.login)
        loginButton.setOnClickListener {
            val user = txtuser.text.toString().trim()
            val password = txtPassword.text.toString().trim()
            if (user.isEmpty() || password.isEmpty()) {
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Inicio de Sesión")
                alert.setMessage("Los campos no pueden estar vacios")
                alert.setPositiveButton("Aceptar") { dialog, which ->
                    dialog.dismiss()
                }
                alert.show()
            }else{
                service.getLogin(
                    CredentialsPOST(
                        txtuser.text.toString(),
                        txtPassword.text.toString(),
                        chkClient.isChecked
                    )
                )
            }
        }
    }

    override fun login(status: Boolean, isEmpleado: Boolean) {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Inicio de Sesión")
        alert.setMessage( if(status) "Inicio de sesión exitoso" else "Inicio de sesión fallido")
        alert.setPositiveButton("Aceptar") { dialog, which ->
            if(status){
                start(isEmpleado)
            }
            dialog.dismiss()
        }
        alert.show()
    }

    private fun start(isEmpleado:Boolean){
        when(isEmpleado){
            true -> {
                val intent = Intent(this, DashBoardRepartidoresActivity::class.java)
                startActivity(intent)
                finish()
            }
            false -> {
                val intent = Intent(this, DashboardClient::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
