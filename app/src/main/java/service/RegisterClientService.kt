package service

import model.common.ApiResponseBody
import model.dto.POST.ClientPOST

interface RegisterClientService {
    fun saveClientValidation(e : ClientPOST, code : Int?)
    fun saveClient(e: ClientPOST)
    fun updateClient(Id: Int, e: ClientPOST)
    fun sendEmail(titulo: String, mensaje: String, remitente: String)
    fun loadClient(id: Int)

}
