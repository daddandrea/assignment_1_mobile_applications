package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_login)

        val emailInputLayout: TextInputLayout = findViewById(R.id.login_email_input_layout)
        var emailInput: TextInputEditText

        val passwordInputLayout: TextInputLayout = findViewById(R.id.login_password_input_layout)
        var passwordInput: TextInputEditText

        val loginButton: Button = findViewById(R.id.register_next_button)

        val credentialsManager = CredentialsManager()

        super.onCreate(savedInstanceState)

        loginButton.setOnClickListener {
            emailInput = findViewById(R.id.login_email_input)
            passwordInput = findViewById(R.id.register_password_input)

            emailInputLayout.error =
                if (!credentialsManager.isEmailValid(emailInput.text.toString())) "Please insert a valid email" else null
            passwordInputLayout.error =
                if (!credentialsManager.isPasswordValid(passwordInput.text.toString())) "Please insert a valid password" else null

            if (credentialsManager.login(
                    emailInput.text.toString(),
                    passwordInput.text.toString()
                )
            ) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            } else {
                loginButton.error = "Email or password are not right"
            }
        }

        val registerLink: TextView = findViewById(R.id.register_now_link)
        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}