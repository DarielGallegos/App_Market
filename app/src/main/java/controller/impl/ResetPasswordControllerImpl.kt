package controller.impl

import android.content.Context
import controller.ResetPasswordController
import view.ResetPasswordView

class ResetPasswordControllerImpl(context: Context) : ResetPasswordController {
    val view = context as ResetPasswordView
    override fun resetPasswd(status: Boolean) {
        view.getStatus(status)
    }
}