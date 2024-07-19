package view.validations

class ValidationResetPassword {
    fun validateFields(e: String): Boolean{
        return e.trim().isNotEmpty()
    }

    fun validateEqualFields(e: String, e2: String): Boolean{
        return e == e2
    }
}