package service

interface PedidoDisponibleService {
    fun getPedidoDisponible(numPedido: Int)
    fun acceptPedido(numPedido: Int, idUsuario: Int)
    fun finishPedido(numPedido: Int)
}