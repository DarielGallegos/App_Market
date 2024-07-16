package service

import model.dto.POST.ClientPOST

interface RegisterClientService {
    fun saveClient(e: ClientPOST)
    fun sendEmail(titulo: String, mensaje: String, remitente: String)
}