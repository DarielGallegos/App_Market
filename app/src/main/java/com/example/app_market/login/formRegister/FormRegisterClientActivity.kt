package com.example.app_market.login.formRegister

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_market.R
import com.example.app_market.login.LoginActivity
import model.dto.POST.ClientPOST
import service.impl.RegisterClientServiceImpl
import utils.Converters
import utils.Permissions
import view.RegisterClientView
import view.validations.ValidationAlertFormRegister
import kotlin.random.Random

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
    private lateinit var btnBack: ImageView
    private var service = RegisterClientServiceImpl(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_register_client)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.colorPrimaryDark, theme)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Assigned value to component lateinit
        image = findViewById(R.id.imgPedido)
        txtName = findViewById(R.id.txtRegNombre)
        txtLastName = findViewById(R.id.txtTotalAdm)
        txtDate = findViewById(R.id.txtRegFecha)
        txtEmail = findViewById(R.id.txtRegCorreo)
        txtPhone = findViewById(R.id.txtRegTelefono)
        txtUser = findViewById(R.id.txtRegUser)
        txtPasswd = findViewById(R.id.txtRegPassword)
        txtPasswdConfirm = findViewById(R.id.txtRegPasswordConfirm)
        cboGender = findViewById(R.id.txtRegGenero)
        btnBack = findViewById(R.id.imgBack)
        btnRegister = findViewById(R.id.btn_guardar)
        val validator = ValidationAlertFormRegister(this)
        btnRegister.setOnClickListener {
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
            val status = validator.validateForm(ClientPOST(nombre, apellido, fecha, genero, correo, telefono, img, usuario, passwd, 2, "Admin"), passwdConfirm)
            if(status){
                val code = Random.nextInt(100000)
                service.saveClientValidation(ClientPOST(nombre, apellido, fecha, genero, correo, telefono, img, usuario, passwd, 2, "Admin"), code)
            }
        }
        /*btnBack.setOnClickListener{
            finish()
        }*/

       btnBack.setOnClickListener{
            val intent = Intent(this@FormRegisterClientActivity, LoginActivity::class.java)
            startActivity(intent)
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
            flush()
        }
        builderDialog.setMessage(msg)
        builderDialog.setPositiveButton("Aceptar") { dialog, which ->
            dialog.dismiss()
        }
        builderDialog.show()

    }



    private fun flush(){
        txtName.text.clear()
        txtLastName.text.clear()
        txtDate.text.clear()
        txtEmail.text.clear()
        txtPhone.text.clear()
        txtUser.text.clear()
        txtPasswd.text.clear()
        txtPasswdConfirm.text.clear()
        cboGender.setSelection(0)
    }
}