package com.example.myapplication

import android.content.Intent
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

class LoginFragment(private val credentialsManager: CredentialsManager) :
    Fragment(R.layout.fragment_login) {
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton = view.findViewById<Button>(R.id.login_next_button)
        loginButton.setOnClickListener {
            handleLogin(view)
        }

        val registerNowLink = view.findViewById<TextView>(R.id.register_now_link)
        registerNowLink.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.authentication_fragment_container_view,
                    RegisterFragment(credentialsManager)
                )
                .addToBackStack(null)
                .commit()
        }
    }

    private fun handleLogin(view: View) {
        emailInputLayout =
            view.findViewById(R.id.login_email_input_layout)
        passwordInputLayout =
            view.findViewById(R.id.login_password_input_layout)

        emailInput = view.findViewById(R.id.login_email_input)
        passwordInput = view.findViewById(R.id.login_password_input)

        val emailText = emailInput.text.toString()
        val passwordText = passwordInput.text.toString()

        emailInputLayout.error =
            if (!credentialsManager.isEmailValid(emailText)) "Please insert a valid email." else null
        passwordInputLayout.error =
            if (!credentialsManager.isPasswordValid(passwordText)) "Please insert a valid password." else null

        Log.d("Debug", "emailText: '$emailText'")
        Log.d("Debug", "passwordText: '$passwordText'")

        if (credentialsManager.login(emailText, passwordText)) {
            Log.d("Debug", "Login successful with: $emailText --> $passwordText")
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}