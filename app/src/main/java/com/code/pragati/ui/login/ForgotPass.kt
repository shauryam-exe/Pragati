package com.code.pragati.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
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

        correctOtp.visibility = View.INVISIBLE
        incorrectOtp.visibility = View.INVISIBLE

        back.setOnClickListener{
            onBackPressed()
        }

        continueLogin.setOnClickListener {
            startActivity(Intent(this, LoginOthers::class.java))
        }

//        val otp = intent.getStringExtra("otp")
//        verifyOtp(otp!!)
//        Toast.makeText(this, "OTP after intent is : $otp", Toast.LENGTH_LONG).show()

    }

    private fun verifyOtp(otp : String) {

        enterOtp.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0?.length == 6){
                    if (p0.equals(otp)){
                        newPass.isEnabled = true
                        newPass.alpha = 1f
                        confirmPass.alpha = 1f
                        continueLogin.alpha = 1f
                        confirmPass.isEnabled = true
                        continueLogin.isEnabled = true
                        correctOtp.visibility = View.VISIBLE
                    } else {
                        incorrectOtp.visibility = View.VISIBLE
                    }
                }
            }
        })

    }
}