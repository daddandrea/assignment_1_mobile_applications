package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity(R.layout.activity_authentication) {
    private lateinit var binding: ActivityAuthenticationBinding
    private val credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("Debug", "Credentials before Registering")
        credentialsManager.logCredentials()

        if (savedInstanceState == null)
            loadFragmentView(RegisterFragment(credentialsManager))
    }

    private fun loadFragmentView(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authentication_fragment_container_view, fragment)
            .commit()
    }
}