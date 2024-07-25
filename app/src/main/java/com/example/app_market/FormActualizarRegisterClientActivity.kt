package com.example.app_market

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.dto.REQUEST.ClientData
import service.impl.RegisterClientServiceImpl

import storage.StoragePreferences
import utils.Converters
import utils.Permissions
import view.RegisterActualizarClientView


class FormActualizarRegisterClientActivity : AppCompatActivity(), RegisterActualizarClientView {
    private lateinit var image: ImageView
    private lateinit var btnRegister: Button
    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtDate: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtPhone: EditText
    private lateinit var txtUser: EditText
    private lateinit var txtPasswd: EditText
    private lateinit var txtPasswdConfirm: EditText
    private lateinit var cboGender: Spinner

    private var storage = StoragePreferences.getInstance(this)
    private var service = RegisterClientServiceImpl(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_actualizar_client)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch(Dispatchers.IO) {
            storage.getCredentiales().collect {
                withContext(Dispatchers.Main) {
                    if (it.id != null) { service.loadClient(it.id) }
                }
            }
        }
        image = findViewById(R.id.imgUserReg)
        txtName = findViewById(R.id.txtRegNombre)
        txtLastName = findViewById(R.id.txtRegApellido)
        txtDate = findViewById(R.id.txtRegFecha)
        txtEmail = findViewById(R.id.txtRegCorreo)
        txtPhone = findViewById(R.id.txtRegTelefono)
        txtUser = findViewById(R.id.txtRegUser)
        txtPasswd = findViewById(R.id.txtRegPassword)
        txtPasswdConfirm = findViewById(R.id.txtRegPasswordConfirm)
        cboGender = findViewById(R.id.txtRegGenero)
        btnRegister = findViewById(R.id.btn_guardar)

        btnRegister.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                //service.updateClient(clientIdToUpdate, Int)
                withContext(Dispatchers.Main) {
                    //statusUpdateClient(response.isSuccessful)
                }
            }
        }
        image.setOnClickListener {
            Permissions().checkCameraPermission(this)
        }
    }

    private fun showError(message: String) {
        val builderDialog = AlertDialog.Builder(this)
        builderDialog.setTitle("Error")
        builderDialog.setMessage(message)
        builderDialog.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
        builderDialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Permissions().REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            image.setImageBitmap(imageBitmap)
        }
    }


    override fun loadClient(it: ClientData, i: Int) {
        txtName.setText(it.nombres)
        txtLastName.setText(it.apellidos)
        txtDate.setText(it.fechaNacimiento)
        txtEmail.setText(it.correo)
        txtPhone.setText(it.telefono)
        txtUser.setText(it.usuario)
        cboGender.setSelection(i)
        val bitmap = Converters().base64ToBitmap(it.foto)
        image.setImageBitmap(bitmap)

    }

    override fun statusUpdateClient(status: Boolean) {
        val builderDialog = AlertDialog.Builder(this)
        builderDialog.setTitle("Actualización de cliente")
        val msg =
            if (status) "Cliente actualizado correctamente" else "Error al actualizar información"
        builderDialog.setMessage(msg)
        builderDialog.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
        builderDialog.show()
    }

}