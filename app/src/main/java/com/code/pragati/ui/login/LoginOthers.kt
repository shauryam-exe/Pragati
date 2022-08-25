package com.code.pragati.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.HomeActivity
import com.code.pragati.R
import com.code.pragati.ui.signUp.WhoAreYou

class LoginOthers : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var forgotPass: TextView
    private lateinit var signUp: TextView
    private lateinit var loginBtn: AppCompatButton

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

        loginBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        forgotPass.setOnClickListener {

//            val otp = (100000..999999).random().toString()
//            Toast.makeText(this, "OTP is : $otp", Toast.LENGTH_SHORT).show()
//
//
//
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.putExtra(Intent.EXTRA_EMAIL, email.text.toString())
//            intent.putExtra(Intent.EXTRA_SUBJECT, "Pragati OTP for password reset")
//            intent.putExtra(
//                Intent.EXTRA_TEXT,
//                "Hey user\nYour six digit one time password is ${otp}\nVerify using this to change the password of your account."
//            )
//            intent.type = "message/rfc882"
//            startActivity(intent)

            val intent2 = Intent(this, ForgotPass::class.java)
//            intent2.putExtra("otp", otp)
            startActivity(intent2)
        }

    }
}