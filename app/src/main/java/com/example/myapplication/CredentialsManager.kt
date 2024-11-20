package com.example.myapplication

class CredentialsManager {
    private val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+")

    fun isEmailValid(email: String): Boolean {
        return Regex(emailPattern).matches(email)
    }
    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
    fun isFullNameValid(fullName: String): Boolean {
        return fullName.isNotEmpty()
    }
    fun isPhoneNumberValid(phoneNumber: String): Boolean {
        return phoneNumber.isNotEmpty()
    }
    fun login(email: String, password: String): Boolean {
        return email == "test" && password == "1234"
    }
    fun register(fullName: String, phoneNumber: String, email: String, password: String): Boolean {
        return fullName.isNotEmpty() && phoneNumber.isNotEmpty() && email == "test" && password == "1234"
    }

}