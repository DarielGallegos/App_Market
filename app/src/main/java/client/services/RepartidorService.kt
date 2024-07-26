package client.services

import model.common.ApiResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface RepartidorService {
    @GET("api/v1/repartidores/{id}")
    fun getPedidosForRepartidor(@Path("id") Id: Int): Call<ApiResponseBody>

    @GET("api/v1/repartidores/unsigned")
    fun getPedidosUnsigned(): Call<ApiResponseBody>

    @GET("api/v1/repartidores/cabecera/{id}")
    fun getCabeceraPedido(@Path("id") Id: Int): Call<ApiResponseBody>

    @GET("api/v1/pedidos/detalle/{id}")
    fun getDetallePedido(@Path("id") Id: Int): Call<ApiResponseBody>

    @PUT("api/v1/repartidores/{numPedido}")
    fun acceptPedido(@Path("numPedido") numPedido: Int, @Query("idUsuario") idUsuario: Int): Call<ApiResponseBody>

    @PUT("api/v1/repartidores/estado/{numPedido}")
    fun finishPedido(@Path("numPedido") numPedido: Int): Call<ApiResponseBody>
}
