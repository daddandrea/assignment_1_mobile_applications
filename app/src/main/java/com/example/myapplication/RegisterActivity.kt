package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val fullNameInputLayout: TextInputLayout =
            findViewById(R.id.register_full_name_input_layout)
        var fullNameInput: TextInputEditText

        val phoneNumberInputLayout: TextInputLayout =
            findViewById(R.id.register_phone_number_input_layout)
        var phoneNumberInput: TextInputEditText

        val emailInputLayout: TextInputLayout = findViewById(R.id.register_valid_email_input_layout)
        var emailInput: TextInputEditText

        val passwordInputLayout: TextInputLayout = findViewById(R.id.register_password_input_layout)
        var passwordInput: TextInputEditText

        val registerButton: Button = findViewById(R.id.register_next_button)
        val credentialsManager = CredentialsManager()

        registerButton.setOnClickListener {
            fullNameInput = findViewById(R.id.register_full_name_input)
            phoneNumberInput = findViewById(R.id.register_phone_number_input)
            emailInput = findViewById(R.id.register_valid_email_input)
            passwordInput = findViewById(R.id.register_password_input)

            fullNameInputLayout.error =
                if (!credentialsManager.isFullNameValid(fullNameInput.text.toString())) "Please insert a full name" else null
            phoneNumberInputLayout.error =
                if (!credentialsManager.isPhoneNumberValid(phoneNumberInput.text.toString())) "Please insert a phone number" else null
            emailInputLayout.error =
                if (!credentialsManager.isEmailValid(emailInput.text.toString())) "Please insert a valid email" else null
            passwordInputLayout.error =
                if (!credentialsManager.isPasswordValid(passwordInput.text.toString())) "Please insert a valid password" else null
        }

        val loginLink: TextView = findViewById(R.id.login_now_link)
        loginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}
