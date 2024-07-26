package controller

import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned

interface PedidosAceptadosController {
    fun setPedidosAceptados(list: List<CabeceraPedidosUnsignedAndSigned>)
}