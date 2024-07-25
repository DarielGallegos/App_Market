package controller.impl

import android.content.Context
import controller.PedidosDisponiblesController
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned
import view.PedidosDisponiblesView

class PedidosDisponiblesControllerImpl(private val context: Context): PedidosDisponiblesController {
    val view = context as PedidosDisponiblesView
    override fun getPedidosDisponibles(list: List<CabeceraPedidosUnsignedAndSigned>) {
        view.initReciclerView(list)
    }
}