package com.code.pragati.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.code.pragati.R

class ForgotPass : AppCompatActivity() {

    private lateinit var back : ImageView
    private lateinit var correctOtp : ImageView
    private lateinit var incorrectOtp : TextView
    private lateinit var enterOtp : EditText
    private lateinit var newPass : EditText
    private lateinit var confirmPass : EditText
    private lateinit var newPassCard : CardView
    private lateinit var confirmPassCard : CardView
    private lateinit var continueLogin : AppCompatButton

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
        newPassCard = findViewById(R.id.cardNewPassForgot)
        confirmPassCard = findViewById(R.id.cardConfirmPassForgot)

        incorrectOtp.visibility = View.INVISIBLE

        back.setOnClickListener{
            onBackPressed()
        }

        continueLogin.setOnClickListener {
            startActivity(Intent(this, LoginOthers::class.java))
        }

        val otp = intent.getStringExtra("otp")
        verifyOtp(otp!!)

    }

    private fun verifyOtp(otp : String) {
        enterOtp.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.contentEquals(otp)) {
                    enableFeatures()
                } else if (p0!!.length == 6 && !p0.contentEquals(otp)) {
                    incorrectOtp.visibility = View.VISIBLE
                } else {
                    disableFeatures()
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun enableFeatures(){
        correctOtp.visibility = View.VISIBLE
        incorrectOtp.visibility = View.INVISIBLE
        continueLogin.isEnabled = true
        newPass.isEnabled = true
        confirmPass.isEnabled = true
        continueLogin.alpha = 1f
        newPassCard.alpha = 1f
        confirmPassCard.alpha = 1f
    }

    private fun disableFeatures(){
        correctOtp.visibility = View.INVISIBLE
        continueLogin.isEnabled = false
        newPass.isEnabled = false
        confirmPass.isEnabled = false
        continueLogin.alpha = 0.3f
        newPassCard.alpha = 0.4f
        confirmPassCard.alpha = 0.4f
    }

}