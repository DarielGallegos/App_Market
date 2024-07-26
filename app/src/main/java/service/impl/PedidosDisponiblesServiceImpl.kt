package service.impl

import android.content.Context
import client.Client
import client.services.RepartidorService
import com.google.gson.Gson
import controller.impl.PedidosDisponiblesControllerImpl
import model.common.ApiResponseBody
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.PedidosDisponiblesService

class PedidosDisponiblesServiceImpl(private val context: Context) : PedidosDisponiblesService {
    private val controller = PedidosDisponiblesControllerImpl(context)
    private val client = Client.ClientRetrofit.getService(RepartidorService::class.java) as RepartidorService
    override fun getPedidosDisponibles() {
        client.getPedidosUnsigned().enqueue(object : Callback<ApiResponseBody> {
            override fun onResponse(call: Call<ApiResponseBody>, response: Response<ApiResponseBody>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    val gson = Gson()
                    val list = gson.fromJson(gson.toJson(body?.data?.content), Array<CabeceraPedidosUnsignedAndSigned>::class.java).toList()
                    controller.getPedidosDisponibles(list)
                }
            }

            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
            }
        })
    }
}