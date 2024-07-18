package service

import model.dto.POST.ClientPOST

interface RegisterClientService {
    fun saveClientValidation(e : ClientPOST, code : Int?)
    fun saveClient(e: ClientPOST)
    fun sendEmail(titulo: String, mensaje: String, remitente: String)
}