package com.example.myapplication

import android.util.Log

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

    private val credentials = hashMapOf(Pair(DUMMY_EMAIL, DUMMY_PASSWORD))

    fun clearCredentials() {
        val initialCredentials = hashMapOf(Pair(DUMMY_EMAIL, DUMMY_PASSWORD))
        credentials.keys.retainAll(initialCredentials.keys)
        credentials[DUMMY_EMAIL] = DUMMY_PASSWORD
    }

    fun logCredentials() {
        for ((key, value) in credentials) {
            Log.d("Debug", "$key --> $value")
        }
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
        return credentials[email.lowercase()] == password
    }

    fun register(fullName: String, phoneNumber: String, email: String, password: String): Boolean {
        if (fullName.isEmpty() || phoneNumber.isEmpty() || !isEmailValid(email) || !isPasswordValid(
                password
            )
        ) return false
        if (credentials.containsKey(email.lowercase())) {
            return false
        } else {
            credentials[email.lowercase()] = password
            return true
        }
    }

}