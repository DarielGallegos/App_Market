package controller.impl

import android.content.Context
import controller.LoginController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import model.dto.REQUEST.Credentials
import storage.StoragePreferences
import view.loginView

class LoginControllerImpl(context: Context) : LoginController {
    private val view = context as loginView
    private val preferences = StoragePreferences.getInstance(context)
    override fun persistLogin(status: Boolean, e: Credentials?) {
        if(status){
            GlobalScope.launch(Dispatchers.IO) {
                preferences.saveCredentials(e!!)
            }
            view.login(status)
        }else{
            view.login(status)
        }
    }
}