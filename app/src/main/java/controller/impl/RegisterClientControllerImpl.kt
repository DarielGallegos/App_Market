package controller.impl

import android.content.Context
import controller.RegisterClientController
import model.dto.REQUEST.ClientData
import view.RegisterActualizarClientView
import view.RegisterClientView

class RegisterClientControllerImpl(context: Context): RegisterClientController {
    private val RegisterClientView = context as RegisterClientView
    private val UpdateClientView = context as RegisterActualizarClientView
    override fun saveClient(status: Boolean) {
        RegisterClientView.statusSaveClient(status)
    }

    override fun updateClient(status: Boolean) {
    }

    override fun loadClient(response: List<ClientData>) {
        UpdateClientView.loadClient(response[0])
    }
}