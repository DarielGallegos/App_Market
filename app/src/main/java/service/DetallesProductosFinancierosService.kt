package service

import model.dto.POST.Pedido

interface DetallesProductosFinancierosService {
    fun pedidosProductos(pedido: Pedido)
}