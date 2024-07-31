package view.validations

import android.app.AlertDialog
import android.content.Context
import model.dto.POST.ClientPOST

class ValidationAlertFormRegister(context: Context) {
    private val ValidationFormRegister = ValidationFormRegister()
    private val alert = AlertDialog.Builder(context)

    private fun validateFields(e: ClientPOST): Boolean {
        return ValidationFormRegister.validateName(e.nombres) &&
                ValidationFormRegister.validateLastName(e.apellidos) &&
                ValidationFormRegister.validateDate(e.fecha_nac) &&
                ValidationFormRegister.validateEmail(e.correo) &&
                ValidationFormRegister.validatePhone(e.telefono) &&
                ValidationFormRegister.validateUser(e.usuario) &&
                ValidationFormRegister.validatePassword(e.passwd)  // Reemplaza passwd con contrasena
    }

    private fun validatePasswordConfirm(e: ClientPOST, passwordConfirm: String): Boolean {
        return ValidationFormRegister.validatePasswordConfirm(e.passwd, passwordConfirm)  // Reemplaza passwd con contrasena
    }

    fun validateForm(e: ClientPOST, passwordConfirm: String) : Boolean{
        val fields = validateFields(e)
        val passwd = validatePasswordConfirm(e, passwordConfirm)
        if(!fields || !passwd) showAlert(fields, passwd)
        return fields && passwd
    }

    fun ValidateFormUpdate(e: ClientPOST) : Boolean {
        val fields = validateFields(e)
        if(!fields) showAlert(fields, true)
        return fields
    }

    private fun showAlert(fields: Boolean, passwd: Boolean){
        alert.setTitle("Registro de cliente")
            .setMessage(
                if(!fields) "Debe completar todos los campos \n Asegurese de ingresar una fecha con el formato YYYY-MM-DD"
                else if(!passwd) "Las contraseñas no coinciden"
                else ""
            )
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
