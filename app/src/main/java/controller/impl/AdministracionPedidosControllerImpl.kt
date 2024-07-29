package controller.impl

import android.content.Context
import controller.AdministracionPedidosController
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned
import view.AdministracionPedidosView

class AdministracionPedidosControllerImpl(private val context: Context) : AdministracionPedidosController {
    private val view = context as AdministracionPedidosView
    override fun getPedidos(list: List<CabeceraPedidosUnsignedAndSigned>) {
        view.initReciclerView(list)
    }
}