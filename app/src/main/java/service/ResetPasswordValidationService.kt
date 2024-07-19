package service

interface ResetPasswordValidationService {
    fun sendMail(email: String)
}