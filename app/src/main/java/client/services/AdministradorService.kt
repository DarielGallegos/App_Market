package client.services

import model.common.ApiResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface AdministradorService {
    @GET("api/v1/administrador/pedidosPendientes")
    fun getPedidosPendientes(): Call<ApiResponseBody>

    @GET("api/v1/administrador/pedidosEntregados")
    fun getPedidosEntregados(): Call<ApiResponseBody>
}