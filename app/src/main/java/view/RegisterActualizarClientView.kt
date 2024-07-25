package view

import model.dto.REQUEST.ClientData

interface RegisterActualizarClientView {


    fun loadClient(it: ClientData, i: Int)

    fun statusUpdateClient(status: Boolean)

}
