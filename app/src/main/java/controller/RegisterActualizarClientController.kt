package controller

import model.common.ApiResponseBody

interface RegisterActualizarClientController {
    fun saveClient()
    fun updateClient(status: Boolean)
    fun loadClient(response: ApiResponseBody?)
}