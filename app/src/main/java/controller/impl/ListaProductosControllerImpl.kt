package controller.impl

import android.content.Context
import controller.ListaProductosController
import model.dto.REQUEST.Pedido
import view.ListaProductosView

class ListaProductosControllerImpl(private val context: Context) : ListaProductosController {
    private val view = context as ListaProductosView
    override fun setDetallePedido(e: Pedido) {
        view.initRecyclerView(e.productos)
    }
}