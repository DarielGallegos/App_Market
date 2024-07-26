package model.dto.POST

data class Pedido(
    val idUsuario: Int = 0,
    val idCliente: Int = 0,
    val destino: String = "",
    val producos: List<ProductoPedido> = emptyList(),
    val impuesto: Int = 1,
    val subtotal: Double = 0.0,
    val envio: Double = 0.0,
    val total: Double = 0.0,
    val estadoPedido: String = "",
    val creadoPor: String = "",
    val estado: Int = 1
)
