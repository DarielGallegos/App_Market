package service.impl

import android.content.Context
import controller.impl.LoginControllerImpl
import service.LoginService

class LoginServiceImpl(context: Context) : LoginService{

    private val controller = LoginControllerImpl(context)
    override fun getLogin(username: String?, password: String?) {
    }
}