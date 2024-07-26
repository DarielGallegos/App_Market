package model.dto.REQUEST

data class Pedido(
    val numeroPedido: Int = 0,
    val cliente: String = "",
    val usuario: String = "",
    val destino: String = "",
    val productos: List<ProductosDetalle> = listOf(),
    val total : Double = 0.0,
    val estado : String = ""
)
