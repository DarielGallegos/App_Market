package controller.impl

import android.content.Context
import controller.DetallesProductosFinancierosController
import view.DetallesProductosFinancierosView

class DetallesProductosFinancierosControllerImpl(context : Context) : DetallesProductosFinancierosController {
    val view = context as DetallesProductosFinancierosView
    override fun setStatus(status: Boolean) {
        view.getStatus(status)
    }

    fun showError(message: String) {
        view.showError(message)
    }
}