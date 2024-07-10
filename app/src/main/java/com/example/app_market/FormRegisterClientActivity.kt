package com.example.app_market

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import model.dto.POST.ClientPOST
import service.impl.RegisterClientServiceImpl
import utils.Converters
import utils.Permissions
import view.RegisterClientView

class FormRegisterClientActivity : AppCompatActivity(), RegisterClientView {
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

    private var service = RegisterClientServiceImpl(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_register_client)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Assigned value to component lateinit
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

        btnRegister = findViewById(R.id.btn_register)
        btnRegister.setOnClickListener {
            var nombre = txtName.text.toString()
            var apellido = txtLastName.text.toString()
            var fecha = txtDate.text.toString()
            var correo = txtEmail.text.toString()
            var telefono = txtPhone.text.toString()
            var generoChar = cboGender.selectedItem.toString()[0]
            var genero = generoChar.toString()
            var usuario = txtUser.text.toString()
            var passwd = txtPasswd.text.toString()
            var passwdConfirm = txtPasswdConfirm.text.toString()
            var img = Converters().bitmapToBase64(image.drawable.toBitmap()) as String
            service.saveClient(ClientPOST(nombre, apellido, fecha, genero, correo, telefono, img, usuario, passwdConfirm, 2, "Admin", 1))
        }
        image.setOnClickListener {
            Permissions().checkCameraPermission(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Permissions().REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            image.setImageBitmap(imageBitmap)
        }
    }

    override fun statusSaveClient(status: Boolean) {
        val builderDialog = AlertDialog.Builder(this)
        builderDialog.setTitle("Registro de cliente")
        var msg = "Error al guardar información"
        if(status){
            msg = "Cliente registrado correctamente"
        }
        builderDialog.setMessage(msg)
        builderDialog.setPositiveButton("Aceptar") { dialog, which ->
            dialog.dismiss()
        }
        builderDialog.show()

    }
}