package service.impl

import android.content.Context
import client.Client
import client.services.RepartidorService
import com.google.gson.Gson
import controller.impl.PedidosAceptadosControllerImpl
import model.common.ApiResponseBody
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.PedidosAceptadosService

class PedidosAceptadosServiceImpl(private val context: Context) : PedidosAceptadosService {
    private val controller = PedidosAceptadosControllerImpl(context)
    private val client = Client.ClientRetrofit.getService(RepartidorService::class.java) as RepartidorService
    override fun getPedidosAceptados(idUser: Int) {
        client.getPedidosForRepartidor(idUser).enqueue(object: Callback<ApiResponseBody> {
            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ApiResponseBody>,
                response: Response<ApiResponseBody>
            ) {
                if(response.isSuccessful) {
                    val body = response.body()
                    val gson = Gson()
                    val list = gson.fromJson(gson.toJson(body?.data?.content), Array<CabeceraPedidosUnsignedAndSigned>::class.java).toList()
                    controller.setPedidosAceptados(list)
                }
            }
        })
    }
}