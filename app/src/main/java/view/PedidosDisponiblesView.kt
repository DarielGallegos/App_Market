package view

import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned

interface PedidosDisponiblesView {
    fun initReciclerView(list: List<CabeceraPedidosUnsignedAndSigned>)
}