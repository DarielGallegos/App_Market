package service

import model.dto.POST.CredentialsPOST

interface LoginService {
    fun getLogin(e: CredentialsPOST)
}