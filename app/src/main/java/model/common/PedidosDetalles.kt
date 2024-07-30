package model.common

import model.dto.REQUEST.ProductosDetalle


class PedidosDetalles (

    var numeroPedido: Int = 0,
    var cliente: String = "",
    var usuario: String = "",
    var destino: String = "",
    var productos: List<ProductosDetalle> = listOf(),
    var total: Double = 0.0,
    var estado : String = ""
    )
