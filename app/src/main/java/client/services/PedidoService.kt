package client.services

import model.common.ApiResponseBody
import retrofit2.Call
import retrofit2.http.GET
import model.common.Pedidos




interface PedidoService {
        @GET("api/v1/pedidos/")
        fun listarPedidos(): Call<ApiResponseBody>
}