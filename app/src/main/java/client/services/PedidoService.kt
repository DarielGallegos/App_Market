package client.services

import model.common.ApiResponseBody
import model.dto.POST.ClientPOST
import model.dto.POST.CredentialsPOST
import model.dto.POST.Pedido
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PedidoService {
    @POST("api/v1/pedidos/")
    fun pedidos(@Body e: Pedido): Call<ApiResponseBody>
}