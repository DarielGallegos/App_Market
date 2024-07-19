package service

interface ResetPasswordService {
    fun resetPassword(email: String, passwd: String)
}