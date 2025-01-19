package com.example.myapplication

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

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
        val loginSuccess = credentials[email.lowercase()] == password
        if (loginSuccess) { _isLoggedIn.value = true }
        return loginSuccess
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

    fun logout() {
        _isLoggedIn.value = false
    }
}