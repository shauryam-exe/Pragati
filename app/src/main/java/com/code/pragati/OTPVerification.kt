package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class OTPVerification : AppCompatActivity() {

    private lateinit var incorrectOTP : TextView
    private lateinit var resendOTP : TextView
    private lateinit var changeNumber : TextView
    private lateinit var otp : EditText
    private lateinit var btnVerifyOtp : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverification)

        incorrectOTP = findViewById(R.id.tvIncorrectOTP)
        resendOTP = findViewById(R.id.tvResendOTP)
        changeNumber = findViewById(R.id.tvChangeNumber)
        otp = findViewById(R.id.etEnterOTP)
        btnVerifyOtp = findViewById(R.id.btnVerifyOTP)

    }
}