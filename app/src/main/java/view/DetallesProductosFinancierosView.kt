package view

interface DetallesProductosFinancierosView {
    fun getStatus(status: Boolean)
    fun showError(message: String)
}