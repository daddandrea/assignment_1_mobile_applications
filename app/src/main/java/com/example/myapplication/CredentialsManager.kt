package com.example.myapplication

private const val DUMMY_EMAIL = "test@te.st"
private const val DUMMY_PASSWORD = "1234"

class CredentialsManager {
    private val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+")

    private val credentials = hashMapOf<String, String>(Pair(DUMMY_EMAIL, DUMMY_PASSWORD))

    fun clearCredentials() {
        val initialCredentials = hashMapOf<String, String>(Pair(DUMMY_EMAIL, DUMMY_PASSWORD))
        credentials.keys.retainAll(initialCredentials.keys)
        credentials[DUMMY_EMAIL] = DUMMY_PASSWORD
    }

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
        return credentials.containsKey(email.lowercase()) && password == credentials[email]
    }

    fun register(fullName: String, phoneNumber: String, email: String, password: String): Boolean {
        if (credentials.containsKey(email.lowercase())) {
            return false
        } else {
            return true
        }
    }

    fun saveRegister(email: String, password: String) {
        credentials[email.lowercase()] = password
    }
}