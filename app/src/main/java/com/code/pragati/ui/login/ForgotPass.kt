package com.code.pragati.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.R

class ForgotPass : AppCompatActivity() {

    private lateinit var back : ImageView
    private lateinit var correctOtp : ImageView
    private lateinit var incorrectOtp : TextView
    private lateinit var enterOtp : EditText
    private lateinit var newPass : EditText
    private lateinit var confirmPass : EditText
    private lateinit var continueLogin : AppCompatButton
    private lateinit var verify : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        back = findViewById(R.id.ivBackForgot)
        correctOtp = findViewById(R.id.ivCorrectOTPForgot)
        incorrectOtp = findViewById(R.id.tvIncorrectOTPForgot)
        enterOtp = findViewById(R.id.etEnterOTPForgot)
        newPass = findViewById(R.id.etNewPassForgot)
        confirmPass = findViewById(R.id.etConfirmPassForgot)
        continueLogin = findViewById(R.id.btnContinueLoginForgot)
        verify = findViewById(R.id.btnVerifyOTPForgot)

        back.setOnClickListener{
            onBackPressed()
        }

        continueLogin.setOnClickListener {
            startActivity(Intent(this, LoginOthers::class.java))
        }

        verify.setOnClickListener {
            verifyOtp()
        }

    }

    private fun verifyOtp() {

    }
}