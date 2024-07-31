package model.dto.REQUEST

data class CabeceraPedidoDataContact(
    val pedidoNumero : Int = 0,
    val telefono: String = "",
    val total: Double = 0.0,
    val ubicacion: String = "",
    val cliente: String ="",
    val correo: String = ""
)
