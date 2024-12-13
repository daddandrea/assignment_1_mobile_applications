package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment(private val credentialsManager: CredentialsManager) :
    Fragment(R.layout.fragment_register) {

    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var phoneNumberInputLayout: TextInputLayout
    private lateinit var fullNameInputLayout: TextInputLayout

    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var phoneNumberInput: TextInputEditText
    private lateinit var fullNameInput: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerButton = view.findViewById<Button>(R.id.register_next_button)
        registerButton.setOnClickListener {
            handleRegister(view)
        }

        val loginNowLink = view.findViewById<TextView>(R.id.login_now_link)
        loginNowLink.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.authentication_fragment_container_view,
                    LoginFragment(credentialsManager)
                )
                .addToBackStack(null)
                .commit()
        }
    }

    private fun handleRegister(view: View) {
        emailInputLayout =
            view.findViewById(R.id.register_valid_email_input_layout)
        passwordInputLayout =
            view.findViewById(R.id.register_password_input_layout)
        fullNameInputLayout =
            view.findViewById(R.id.register_full_name_input_layout)
        phoneNumberInputLayout =
            view.findViewById(R.id.register_phone_number_input_layout)

        emailInput =
            view.findViewById(R.id.register_valid_email_input)
        passwordInput =
            view.findViewById(R.id.register_password_input)
        fullNameInput =
            view.findViewById(R.id.register_full_name_input)
        phoneNumberInput =
            view.findViewById(R.id.register_phone_number_input)

        val emailText = emailInput.text.toString()
        val passwordText = passwordInput.text.toString()
        val fullNameText = fullNameInput.text.toString()
        val phoneNumberText = phoneNumberInput.text.toString()

        emailInputLayout.error =
            if (!credentialsManager.isEmailValid(emailText)) "Please insert a valid email." else null
        passwordInputLayout.error =
            if (!credentialsManager.isPasswordValid(passwordText)) "Please insert a valid password." else null
        fullNameInputLayout.error =
            if (!credentialsManager.isFullNameValid(fullNameText)) "Please insert a valid full name." else null
        phoneNumberInputLayout.error =
            if (!credentialsManager.isPhoneNumberValid(phoneNumberText)) "Please insert a valid phone number." else null

        if (credentialsManager.register(fullNameText, phoneNumberText, emailText, passwordText)) {
            Log.d("Debug", "Credentials after Registering")
            credentialsManager.logCredentials()
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.authentication_fragment_container_view,
                    LoginFragment(credentialsManager)
                )
                .commit()
        }
    }
}