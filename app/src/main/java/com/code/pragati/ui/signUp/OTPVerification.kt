package com.code.pragati.ui.signUp

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.code.pragati.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest

class OTPVerification : AppCompatActivity() {

    private lateinit var incorrectOTP: TextView
    private lateinit var resendOTP: TextView
    private lateinit var changeNumber: TextView
    private lateinit var otp: EditText
    private lateinit var verifyOtp: Button

    private lateinit var auth: FirebaseAuth

    private lateinit var backendOTP: String
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverification)

        incorrectOTP = findViewById(R.id.tvIncorrectOTP)
        resendOTP = findViewById(R.id.tvResendOTP)
        changeNumber = findViewById(R.id.tvChangeNumber)
        otp = findViewById(R.id.etEnterOTP)
        verifyOtp = findViewById(R.id.btnVerifyOTP)

        changeNumber.setOnClickListener {
            onBackPressed()
        }

        auth = FirebaseAuth.getInstance()

        incorrectOTP.visibility = View.GONE

        backendOTP = intent.getStringExtra("otp").toString()
        name = intent.getStringExtra("username").toString()


        verifyOtp.setOnClickListener {
            val userOTP = otp.text.toString().trim()
            if (TextUtils.isEmpty(userOTP)) {
                Toast.makeText(this, "Please enter OTP first.", Toast.LENGTH_SHORT).show()
            } else {
                val phoneAuthCredential = PhoneAuthProvider.getCredential(backendOTP, userOTP)
                auth.signInWithCredential(phoneAuthCredential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("check", "FirebaseAuth credentials verified")
                            val user = auth.currentUser
                            val profileUpdates =
                                UserProfileChangeRequest.Builder().setDisplayName(name).build()
                            user!!.updateProfile(profileUpdates)
                            incorrectOTP.visibility = View.GONE
                            Toast.makeText(this, "correct otp", Toast.LENGTH_SHORT).show()
                            //                TODO("Handle UI")
//                val intent = Intent(this, ChatInside::class.java)
//                startActivity(intent)
//                finish()

                        } else {
                            Log.d("check", "Enter correct otp")
                            incorrectOTP.visibility = View.VISIBLE
                            Toast.makeText(this, "Enter correct otp", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
}