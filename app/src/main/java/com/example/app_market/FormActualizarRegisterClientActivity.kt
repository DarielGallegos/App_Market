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
import androidx.core.graphics.drawable.toBitmap

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.dto.POST.ClientPOST
import model.dto.REQUEST.ClientData
import service.impl.RegisterClientServiceImpl

import storage.StoragePreferences
import utils.Converters
import utils.Permissions
import view.RegisterActualizarClientView
import view.validations.ValidationAlertFormRegister


class FormActualizarRegisterClientActivity : AppCompatActivity(), RegisterActualizarClientView {
    private lateinit var image: ImageView
    private lateinit var btnRegister: Button
    private lateinit var btnBack: ImageView
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
    private lateinit var validator : ValidationAlertFormRegister

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
        validator = ValidationAlertFormRegister(this)
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
        btnBack = findViewById(R.id.imgBackPut)

        btnRegister.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                storage.getCredentiales().collect {
                    withContext(Dispatchers.Main) {
                        if (it.id != null) {
                            updateClient(it.id)
                        }
                    }
                }
            }
        }
        image.setOnClickListener {
            Permissions().checkCameraPermission(this)
        }
    }

    private fun updateClient(id: Int){
        val nombre = txtName.text.toString().trim()
        val apellido = txtLastName.text.toString().trim()
        val fecha = txtDate.text.toString().trim()
        val correo = txtEmail.text.toString().trim()
        val telefono = txtPhone.text.toString().trim()
        val generoChar = cboGender.selectedItem.toString()[0]
        val genero = generoChar.toString().trim()
        val usuario = txtUser.text.toString().trim()
        val passwd = txtPasswd.text.toString().trim()
        val passwdConfirm = txtPasswdConfirm.text.toString().trim()
        val img = Converters().bitmapToBase64(image.drawable.toBitmap()) as String
        if(passwd.isEmpty()){
            val status = validator.ValidateFormUpdate(ClientPOST(nombre, apellido, fecha, genero, correo, telefono, img, usuario, "l", 2, "Admin"))
            if(status) {
                service.updateClient(id, ClientPOST(nombre, apellido, fecha, genero, correo, telefono, img, usuario, "", 2, "Admin"))
            }
        }else{
            val status = validator.validateForm(ClientPOST(nombre, apellido, fecha, genero, correo, telefono, img, usuario, passwd, 2, "Admin"), passwdConfirm)
            if(status){
                service.updateClient(id, ClientPOST(nombre, apellido, fecha, genero, correo, telefono, img, usuario, passwd, 2, "Admin"))
            }
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