package model.dto.POST

data class ClientPOST(

    val nombres: String,
    val apellidos: String,
    val fecha_nac: String,
    val genero: String,
    val correo: String,
    val telefono: String,
    val foto: String,
    val usuario: String,
    val passwd: String,
    val id_rol: Int,
    val creado_por: String
)
