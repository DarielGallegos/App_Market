package service

import model.common.PedidoValorar

interface PedidosSinValorarService {
    fun getPedidosSValorar(idUser: Int, cb:(list_pedidos: List<PedidoValorar>?)->Unit)
}