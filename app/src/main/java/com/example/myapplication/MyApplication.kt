package com.example.myapplication

import android.app.Application

class MyApplication : Application() {
    val credentialsManager = CredentialsManager()

    companion object {
        private lateinit var instance: MyApplication
        fun getInstance(): MyApplication = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}