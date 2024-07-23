package client.services

import model.common.ApiResponseBody
import retrofit2.Call
import retrofit2.http.GET
import model.common.Pedidos
import retrofit2.http.Path


interface PedidoService {
        @GET("api/v1/pedidos/{id}")
        fun listarPedidos(@Path("id")id:Int): Call<ApiResponseBody>
}