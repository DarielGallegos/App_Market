package service.impl

import android.content.Context
import android.util.Log
import client.Client
import client.services.RepartidorService
import com.google.gson.Gson
import controller.impl.ListaProductosControllerImpl
import model.common.ApiResponseBody
import model.dto.REQUEST.Pedido
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.ListaProductosService

class ListaProductosServiceImpl(private val context: Context) : ListaProductosService {
    private val controller = ListaProductosControllerImpl(context)
    private val client = Client.ClientRetrofit.getService(RepartidorService::class.java) as RepartidorService
    override fun getDetallePedido(numPedido: Int) {
        client.getDetallePedido(numPedido).enqueue(object : Callback<ApiResponseBody> {
            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ApiResponseBody>,
                response: Response<ApiResponseBody>
            ) {
                if(response.isSuccessful){
                    val body = response.body()?.data?.content
                    val gson = Gson()
                    val pedido = gson.fromJson(gson.toJson(body), Array<Pedido>::class.java).toList()
                    controller.setDetallePedido(pedido[0])
                }
            }
        })
    }
}