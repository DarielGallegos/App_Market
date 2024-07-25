package model.dto.POST

data class ClientPOST(

    val nombre: String,
    val apellido: String,
    val fechaNacimiento: String,
    val genero: String,
    val correo: String,
    val telefono: String,
    val imagen: String,
    val usuario: String,
    val contrasena: String,
    val tipo: Int,
    val rol: String,
    val estado: Int
)
