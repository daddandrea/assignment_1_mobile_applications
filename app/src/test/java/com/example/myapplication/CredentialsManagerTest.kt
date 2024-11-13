package com.example.myapplication

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CredentialsManagerTest {
    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val email = ""
        val result = credentialsManager.isEmailValid(email)

        assertFalse(result)
    }
    @Test
    fun givenWrongEmailFormat_noAt_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val email = "exampleemail.com"
        val result = credentialsManager.isEmailValid(email)

        assertFalse(result)
    }
    @Test
    fun givenWrongEmailFormat_noDot_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val email = "example@emailcom"
        val result = credentialsManager.isEmailValid(email)

        assertFalse(result)
    }
    @Test
    fun givenWrongEmailFormat_noDotAndNoAt_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val email = "exampleemailcom"
        val result = credentialsManager.isEmailValid(email)

        assertFalse(result)
    }
    @Test
    fun givenProperEmailFormat_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val email = "example@email.com"
        val result = credentialsManager.isEmailValid(email)

        assertTrue(result)
    }
}