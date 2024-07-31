package model.dto.REQUEST

data class CabeceraPedidosUnsignedAndSigned(
    val pedido_numero: Int = 0,
    val usuario: String = "",
    val cliente: String = "",
    val total: Double = 0.0,
    val estado_pedido: String = "")

