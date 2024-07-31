package service.impl

import android.content.Context
import client.Client
import client.services.AdministradorService
import com.google.gson.Gson
import controller.impl.AdministracionPedidosControllerImpl
import model.common.ApiResponseBody
import model.dto.REQUEST.CabeceraPedidosUnsignedAndSigned
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.AdministracionPedidosService

class AdministracionPedidosServiceImpl(private val context: Context) : AdministracionPedidosService {
    private val controller = AdministracionPedidosControllerImpl(context)
    private val client = Client.ClientRetrofit.getService(AdministradorService::class.java) as AdministradorService
    override fun getPedidosPendientes() {
        client.getPedidosPendientes().enqueue(object: Callback<ApiResponseBody> {
            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ApiResponseBody>,
                response: Response<ApiResponseBody>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    val gson = Gson()
                    val list = gson.fromJson(gson.toJson(body?.data?.content), Array<CabeceraPedidosUnsignedAndSigned>::class.java).toList()
                    controller.getPedidos(list)
                }
            }

        })
    }

    override fun getPedidosEntregados() {
        client.getPedidosEntregados().enqueue(object: Callback<ApiResponseBody> {
            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ApiResponseBody>,
                response: Response<ApiResponseBody>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    val gson = Gson()
                    val list = gson.fromJson(gson.toJson(body?.data?.content), Array<CabeceraPedidosUnsignedAndSigned>::class.java).toList()
                    controller.getPedidos(list)
                }
            }

        })

    }
}