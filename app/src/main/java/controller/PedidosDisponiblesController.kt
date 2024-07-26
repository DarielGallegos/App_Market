package controller

import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned

interface PedidosDisponiblesController {
    fun getPedidosDisponibles(list: List<CabeceraPedidosUnsignedAndSigned>)
}