package service

import model.common.ApiResponseBody
import model.dto.POST.ClientPOST

interface RegisterClientService {
    fun saveClientValidation(e : ClientPOST, code : Int?)
    fun saveClient(e: ClientPOST)
    fun updateClient(clientIdToUpdate: Int, clientPOST: Int)
    fun sendEmail(titulo: String, mensaje: String, remitente: String)
    fun loadClient(id: Int)

}
