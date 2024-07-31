package view

import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned

interface AdministracionPedidosView {
    fun initReciclerView(list: List<CabeceraPedidosUnsignedAndSigned>)
}