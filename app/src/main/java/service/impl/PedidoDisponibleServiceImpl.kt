package service.impl

import android.content.Context
import client.Client
import client.services.RepartidorService
import com.google.gson.Gson
import controller.impl.PedidoDisponibleControllerImpl
import model.common.ApiResponseBody
import model.dto.REQUEST.CabeceraPedidoDataContact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.PedidoDisponibleService

class PedidoDisponibleServiceImpl(private val context: Context) : PedidoDisponibleService {
    private val controller = PedidoDisponibleControllerImpl(context)
    private val client = Client.ClientRetrofit.getService(RepartidorService::class.java) as RepartidorService
    override fun getPedidoDisponible(numPedido: Int) {
        client.getCabeceraPedido(numPedido).enqueue( object: Callback<ApiResponseBody> {
            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ApiResponseBody>,
                response: Response<ApiResponseBody>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    val gson = Gson()
                    val pedido = gson.fromJson(gson.toJson(body?.data?.content), Array<CabeceraPedidoDataContact>::class.java).toList()
                    controller.setPedidoDisponible(pedido)
                }
            }

        })
    }

    override fun acceptPedido(numPedido: Int, idUsuario: Int) {
        client.acceptPedido(numPedido, idUsuario).enqueue(object: Callback<ApiResponseBody> {
            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ApiResponseBody>,
                response: Response<ApiResponseBody>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    val gson = Gson()
                    val msg = gson.fromJson(gson.toJson(body?.data?.msg), Array<String>::class.java).toList()
                    controller.acceptPedido(msg[0])
                }
            }
        })
    }

    override fun finishPedido(numPedido: Int) {
        client.finishPedido(numPedido).enqueue(object: Callback<ApiResponseBody> {
            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ApiResponseBody>,
                response: Response<ApiResponseBody>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    val gson = Gson()
                    val msg = gson.fromJson(gson.toJson(body?.data?.msg), Array<String>::class.java).toList()
                    controller.finishPedido(msg[0])
                }
            }
        })
    }
}