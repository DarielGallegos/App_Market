package client.services

import model.common.ApiResponseBody
import model.common.PedidoValorar
import model.dto.POST.ClientPOST
import model.dto.POST.CredentialsPOST
import model.dto.POST.Pedido
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PedidoService {
    @POST("api/v1/pedidos/")
    fun pedidos(@Body e: Pedido): Call<ApiResponseBody>

    @GET("api/v1/pedidos/entregados/{idCliente}")
    fun getPedidosSValorar(@Path("idCliente") IdCliente: Int): Call<ApiResponseBody>

    @GET("api/v1/pedidos/finalizado/{numPedido}")
    fun pedidoFinalizado(@Path("numPedido") NumPedido: Int): Call<ApiResponseBody>
}