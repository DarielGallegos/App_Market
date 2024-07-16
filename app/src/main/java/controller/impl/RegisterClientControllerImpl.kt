package controller.impl

import android.content.Context
import controller.RegisterClientController
import view.RegisterClientView

class RegisterClientControllerImpl(context: Context): RegisterClientController {
    private val RegisterClientView = context as RegisterClientView
    override fun saveClient(status: Boolean) {
        RegisterClientView.statusSaveClient(status)
    }

    override fun sendEmail(status: Boolean) {
        RegisterClientView.statusSendEmail(status)
    }
}