package controller

import model.dto.REQUEST.ClientData

interface RegisterClientController {
    fun saveClient(status: Boolean)
    fun updateClient(status: Boolean)
    fun loadClient(response: List<ClientData>)
}