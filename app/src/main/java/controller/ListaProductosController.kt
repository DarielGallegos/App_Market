package controller

import model.dto.REQUEST.Pedido

interface ListaProductosController {
    fun setDetallePedido(e: Pedido)
}