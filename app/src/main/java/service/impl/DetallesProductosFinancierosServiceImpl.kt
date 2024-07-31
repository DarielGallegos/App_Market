package service.impl

import android.content.Context
import android.util.Log
import client.Client
import client.services.PedidoService
import controller.impl.DetallesProductosFinancierosControllerImpl
import model.common.ApiResponseBody
import model.dto.POST.Pedido
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.DetallesProductosFinancierosService
import java.io.IOException

class DetallesProductosFinancierosServiceImpl(context: Context) : DetallesProductosFinancierosService {
    val controller = DetallesProductosFinancierosControllerImpl(context)
    val cliente = Client.ClientRetrofit.getService(PedidoService::class.java) as PedidoService
    override fun pedidosProductos(pedido: Pedido) {
        cliente.pedidos(pedido).enqueue(object : Callback<ApiResponseBody>{
            override fun onResponse(
                call: Call<ApiResponseBody>,
                response: Response<ApiResponseBody>
            ) {
                if(response.isSuccessful){
                    val respuesta = response.body()?.status
                    controller.setStatus(respuesta == "CREATED")
                }
            }

            override fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                Log.e("DetallesProdFinService", "Error en la solicitud de pedido", t)

                controller.setStatus(false)

                if (t is IOException) {
                    controller.showError("Problema de conexión. Por favor, inténtelo de nuevo.")
                } else {
                    controller.showError("Ocurrió un error inesperado. Por favor, inténtelo de nuevo.")
                }
            }

        })
    }
}