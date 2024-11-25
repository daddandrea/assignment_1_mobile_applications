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

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val password = ""
        val result = credentialsManager.isPasswordValid(password)

        assertFalse(result)
    }

    @Test
    fun givenNotEmptyPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val password = "1234"
        val result = credentialsManager.isPasswordValid(password)

        assertTrue(result)
    }

    @Test
    fun givenEmptyFullName_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val fullName = ""
        val result = credentialsManager.isFullNameValid(fullName)

        assertFalse(result)
    }

    @Test
    fun givenNotEmptyFullName_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val fullName = "Andrea D'Addabbo"
        val result = credentialsManager.isFullNameValid(fullName)

        assertTrue(result)
    }

    @Test
    fun givenEmptyPhoneNumber_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val phoneNumber = ""
        val result = credentialsManager.isPhoneNumberValid(phoneNumber)

        assertFalse(result)
    }

    @Test
    fun givenNotEmptyPhoneNumber_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val phoneNumber = "3333333333"
        val result = credentialsManager.isPhoneNumberValid(phoneNumber)

        assertTrue(result)
    }

    @Test
    fun login_givenEmptyCredentials_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val email = ""
        val password = ""
        val result = credentialsManager.login(email, password)

        assertFalse(result)
    }

    @Test
    fun login_givenWrongCredentials_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val email = "not a user"
        val password = "not a password"
        val result = credentialsManager.login(email, password)

        assertFalse(result)
    }

    @Test
    fun login_givenRightCredentials_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val email = "test@te.st"
        val password = "1234"
        val result = credentialsManager.login(email, password)

        assertTrue(result)
    }

    @Test
    fun register_givenEmptyCredentials_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val fullName = ""
        val email = ""
        val phoneNumber = ""
        val password = ""
        val result = credentialsManager.register(fullName, phoneNumber, email, password)

        assertFalse(result)
    }

    @Test
    fun register_givenWrongCredentials_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val fullName = "seffe"
        val email = "fesff"
        val phoneNumber = "fesef"
        val password = "sefesfe"
        val result = credentialsManager.register(fullName, phoneNumber, email, password)

        assertFalse(result)
    }

    @Test
    fun register_givenGoodCredentials_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val fullName = "seffe"
        val email = "test@te.st"
        val phoneNumber = "fesef"
        val password = "1234"
        val result = credentialsManager.register(fullName, phoneNumber, email, password)

        assertTrue(result)
    }
}