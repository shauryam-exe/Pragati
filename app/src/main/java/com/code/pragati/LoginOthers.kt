package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginOthers : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var forgotPass : TextView
    private lateinit var loginBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_others)

        email = findViewById(R.id.etEmailLoginOthers)
        password = findViewById(R.id.etPassLoginOthers)
        forgotPass = findViewById(R.id.tvForgotPassword)
        loginBtn = findViewById(R.id.btnLoginOthers)

    }
}