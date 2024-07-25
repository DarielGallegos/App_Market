package view

import model.common.ApiResponseBody
import model.dto.POST.ClientPOST
import model.dto.REQUEST.ClientData

interface RegisterActualizarClientView {


    fun loadClient(it: ClientData)

    fun statusUpdateClient(status: Boolean)

}
