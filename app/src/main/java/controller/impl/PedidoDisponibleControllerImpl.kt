package controller.impl

import android.content.Context
import controller.PedidoDisponibleController
import model.dto.REQUEST.CabeceraPedidoDataContact
import view.PedidoDisponibleView

class PedidoDisponibleControllerImpl(private val context: Context): PedidoDisponibleController {
    private val view = context as PedidoDisponibleView
    override fun setPedidoDisponible(list: List<CabeceraPedidoDataContact>) {
        view.initView(list[0])
    }

    override fun acceptPedido(msg: String) {
        var status = if(msg == "Pedido Aceptado") "Pedido Aceptado" else "Pedido Rechazado"
        view.acceptPedido(msg, status)
    }

    override fun finishPedido(msg: String) {
        view.finishPedido(msg)
    }
}