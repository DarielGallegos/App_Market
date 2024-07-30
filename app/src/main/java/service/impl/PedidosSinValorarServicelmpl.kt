package service.impl

import android.content.Context
import android.util.Log
import android.widget.Toast
import client.Client
import client.services.PedidoService
import com.example.app_market.adapters.ListPendientesAdapter
import com.example.app_market.databinding.ActivityListaPendienteValoracionBinding
import com.google.gson.Gson
import controller.impl.PedidosAceptadosControllerImpl
import model.common.ApiResponseBody
import model.common.PedidoValorar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.PedidosAceptadosService
import service.PedidosSinValorarService

class PedidosSinValorarServicelmpl (private val context: Context) : PedidosSinValorarService {
    //private val controller = PedidosSinValorarServicelmpl(context)

    var pedidos: List<PedidoValorar>? = null
    var adapter: ListPendientesAdapter? = null

    val apiService = Client.ClientRetrofit.getService(PedidoService::class.java) as PedidoService

    override fun getPedidosSValorar(idUser: Int, cb:(list_pedidos: List<PedidoValorar>?)->Unit) {
        apiService.getPedidosSValorar( IdCliente = idUser ).enqueue(object : Callback<ApiResponseBody> {

            override
            fun onResponse(call: Call<ApiResponseBody>, response: Response<ApiResponseBody>) {
                if (response.isSuccessful) {
                    val gson = Gson()
                    pedidos = gson.fromJson(gson.toJson(response.body()?.data?.content), Array<PedidoValorar>::class.java).toList()
                    cb(pedidos)
                    //cont .listaPendientes.adapter = ListPendientesAdapter(this@ListaPendienteValoracionActivity, pedidos ?: emptyList())

                } else {
                    Toast.makeText(
                        context,
                        "Error: " + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override
            fun onFailure(call: Call<ApiResponseBody>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


}