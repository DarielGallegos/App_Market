package client.services

import model.common.ApiResponseBody
import model.common.Producto
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("api/v1/productos/")
    fun listarProductos(): Call<ApiResponseBody>
}