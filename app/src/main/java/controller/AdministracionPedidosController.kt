package controller

import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned

interface AdministracionPedidosController {
    fun getPedidos(list: List<CabeceraPedidosUnsignedAndSigned>)
}