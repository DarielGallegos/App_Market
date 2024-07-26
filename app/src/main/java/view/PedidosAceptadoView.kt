package view

import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned

interface PedidosAceptadoView {
    fun initReciclerView(list: List<CabeceraPedidosUnsignedAndSigned>)
}