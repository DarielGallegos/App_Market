package model.dto.REQUEST

data class ClientData(
    val id : Int = 0,
    val nombres: String = "",
    val apellidos: String = "",
    val correo: String = "",
    val telefono: String = "",
    val fechaNacimiento : String = "",
    val foto: String = "",
    val genero: String = "",
)
