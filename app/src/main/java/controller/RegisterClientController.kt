package controller

interface RegisterClientController {
    fun saveClient(status: Boolean)
    fun sendEmail(status: Boolean)
}