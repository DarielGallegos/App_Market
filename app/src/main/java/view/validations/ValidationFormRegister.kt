package view.validations

class ValidationFormRegister {
        fun validateName(name: String): Boolean {
            return name.isNotEmpty()
        }

        fun validateLastName(lastName: String): Boolean {
            return lastName.isNotEmpty()
        }

        fun validateDate(date: String): Boolean {
            return date.isNotEmpty()
        }

        fun validateEmail(email: String): Boolean {
            return email.isNotEmpty()
        }

        fun validatePhone(phone: String): Boolean {
            return phone.isNotEmpty()
        }

        fun validateUser(user: String): Boolean {
            return user.isNotEmpty()
        }

        fun validatePassword(password: String): Boolean {
            return password.isNotEmpty()
        }

        fun validatePasswordConfirm(password: String, passwordConfirm: String): Boolean {
            return password == passwordConfirm
        }
}