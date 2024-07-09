package com.example.app_market

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import storage.StoragePreferences
import view.loginView

class LoginActivity : loginView,  AppCompatActivity(){

    private var CREDENTIALES: StoragePreferences? = StoragePreferences(this)
    private lateinit var loginButton: Button
    private lateinit var txtuser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var register: TextView
    private lateinit var forgot: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        txtuser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)

        register = findViewById(R.id.register)
        forgot = findViewById(R.id.forgot)

        register.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, FormRegisterClientActivity::class.java)
            startActivity(intent)
        })

        lifecycleScope.launch(Dispatchers.IO) {
            CREDENTIALES?.getCredentiales()?.collect {
                withContext(Dispatchers.Main) {
                    if(it.id != null){
                        Log.i("Credenciales", "${it.nombre} - ${it.username}")
                    }else{
                        Log.i("Credenciales", "No hay credenciales")
                    }
                }
            }
        }


        loginButton = findViewById(R.id.login)
        loginButton.setOnClickListener {
            val intent = Intent(applicationContext, DashboardClient::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun login(msg: String?) {
    }
}
