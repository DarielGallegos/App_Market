package controller

import model.dto.REQUEST.CabeceraPedidoDataContact

interface PedidoDisponibleController {
    fun setPedidoDisponible(list: List<CabeceraPedidoDataContact>)
    fun acceptPedido(msg: String)
    fun finishPedido(msg: String)
}