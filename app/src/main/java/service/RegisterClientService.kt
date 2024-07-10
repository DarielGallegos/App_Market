package service

import model.dto.POST.ClientPOST

interface RegisterClientService {
    fun saveClient(e: ClientPOST)
}