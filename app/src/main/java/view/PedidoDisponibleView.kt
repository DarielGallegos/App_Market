package view

import model.dto.REQUEST.CabeceraPedidoDataContact

interface PedidoDisponibleView {
    fun initView(e: CabeceraPedidoDataContact)
    fun acceptPedido(msg: String, status: String)
    fun finishPedido(msg: String)
}