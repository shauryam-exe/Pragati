package com.code.pragati

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class LoginOthers : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var forgotPass : TextView
    private lateinit var signUp : TextView
    private lateinit var loginBtn : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_others)

        email = findViewById(R.id.etEmailLoginOthers)
        password = findViewById(R.id.etPassLoginOthers)
        forgotPass = findViewById(R.id.tvForgotPassword)
        signUp = findViewById(R.id.tvSignup)
        loginBtn = findViewById(R.id.btnLoginOthers)

        signUp.setOnClickListener {
            startActivity(Intent(this, WhoAreYou::class.java))
        }

    }
}