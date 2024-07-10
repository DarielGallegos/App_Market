package controller

import model.dto.REQUEST.Credentials

interface LoginController {
    fun persistLogin(status: Boolean, e: Credentials?)
}