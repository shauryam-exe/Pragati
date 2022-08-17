package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OTPVerification : AppCompatActivity() {

    private lateinit var incorrectOTP : TextView
    private lateinit var resendOTP : TextView
    private lateinit var changeNumber : TextView
    private lateinit var otp : EditText
    private lateinit var verifyOtp : Button

    lateinit var backendOTP: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverification)

        incorrectOTP = findViewById(R.id.tvIncorrectOTP)
        resendOTP = findViewById(R.id.tvResendOTP)
        changeNumber = findViewById(R.id.tvChangeNumber)
        otp = findViewById(R.id.etEnterOTP)
        verifyOtp = findViewById(R.id.btnVerifyOTP)

        incorrectOTP.visibility = View.INVISIBLE

        backendOTP = intent.getStringExtra("otp").toString()


        verifyOtp.setOnClickListener {
            val userOTP = otp.text.toString().trim()
            val phoneAuthCredential = PhoneAuthProvider.getCredential(backendOTP, userOTP)
            FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("check","FirebaseAuth credentials verified")
                    } else {
                        Log.d("check","Enter correct otp")
                    }
                }
        }

    }
}