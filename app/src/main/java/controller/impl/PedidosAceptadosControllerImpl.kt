package controller.impl

import android.content.Context
import controller.PedidosAceptadosController
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned
import view.PedidosAceptadoView

class PedidosAceptadosControllerImpl(private val context: Context): PedidosAceptadosController {
    private val view = context as PedidosAceptadoView
    override fun setPedidosAceptados(list: List<CabeceraPedidosUnsignedAndSigned>) {
        view.initReciclerView(list)
    }
}