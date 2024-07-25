package controller.impl

import android.content.Context
import controller.RegisterClientController
import model.dto.REQUEST.ClientData
import view.RegisterActualizarClientView
import view.RegisterClientView

class RegisterClientControllerImpl(context: Context): RegisterClientController {
    val context = context
    override fun saveClient(status: Boolean) {
        val RegisterClientView = context as RegisterClientView
        RegisterClientView.statusSaveClient(status)
    }

    override fun updateClient(status: Boolean) {
    }

    override fun loadClient(response: List<ClientData>) {
        val UpdateClientView = context as RegisterActualizarClientView
        UpdateClientView.loadClient(response[0], if(response[0].genero == "M") 0 else 1)
    }
}