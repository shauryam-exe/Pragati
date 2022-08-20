package com.code.pragati.ui.signUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.code.pragati.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class LoginLayman : AppCompatActivity() {

    private lateinit var number : EditText
    private lateinit var name : EditText
    private lateinit var getOTP : Button
    private lateinit var google : CardView
    private lateinit var linkedIn : CardView
    private lateinit var social : CardView
    private lateinit var back : ImageView

    private lateinit var auth: FirebaseAuth
    private val TAG = "check"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_leyman)

        number = findViewById(R.id.etNumberLogin)
        name = findViewById(R.id.etNameLoginLeyMan)
        getOTP = findViewById(R.id.btnGetOTP)
        google = findViewById(R.id.cardLoginWithGoogle)
        linkedIn = findViewById(R.id.cardLoginWithLinkedIn)
        social = findViewById(R.id.cardLoginWithSocial)
        back = findViewById(R.id.ivBackLoginLayman)

        back.setOnClickListener{
            onBackPressed()
        }

        auth = FirebaseAuth.getInstance()

        getOTP.setOnClickListener {
            val phoneNumber = number.text.toString().trim()
            val nameText = name.text.toString().trim()
            if (phoneNumber.isNotEmpty() && nameText.isNotEmpty()) {
                if (phoneNumber.length == 10) {

                    //ProgressBar enable. Button Disable

                    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                            // This callback will be invoked in two situations:
                            // 1 - Instant verification. In some cases the phone number can be instantly
                            //     verified without needing to send or enter a verification code.
                            // 2 - Auto-retrieval. On some devices Google Play services can automatically
                            //     detect the incoming verification SMS and perform verification without
                            //     user action.
                            Log.d(TAG, "onVerificationCompleted:$credential")

                        }

                        override fun onVerificationFailed(e: FirebaseException) {
                            // This callback is invoked in an invalid request for verification is made,
                            // for instance if the the phone number format is not valid.
                            Log.w(TAG, "onVerificationFailed", e)

                            //Update e.message later
                            Toast.makeText(this@LoginLayman,e.message,Toast.LENGTH_SHORT).show()
                        }

                        override fun onCodeSent(
                            verificationId: String,
                            token: PhoneAuthProvider.ForceResendingToken
                        ) {
                            // The SMS verification code has been sent to the provided phone number, we
                            // now need to ask the user to enter the code and then construct a credential
                            // by combining the code with a verification ID.
                            Log.d(TAG, "onCodeSent:$verificationId")

                            val intent = Intent(this@LoginLayman, OTPVerification::class.java)
                            intent.putExtra("otp",verificationId)
                            startActivity(intent)
                        }
                    }

                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+91$phoneNumber") // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)

                } else {
                    Toast.makeText(this,"Please enter correct mobile number",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"Fields can't be empty",Toast.LENGTH_SHORT).show()
            }
        }
    }
}