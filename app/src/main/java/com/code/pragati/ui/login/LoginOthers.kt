package com.code.pragati.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.HomeActivity
import com.code.pragati.R
import com.code.pragati.ui.signUp.WhoAreYou
import com.google.firebase.auth.FirebaseAuth

class LoginOthers : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var forgotPass: TextView
    private lateinit var signUp: TextView
    private lateinit var loginBtn: AppCompatButton
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_others)

        email = findViewById(R.id.etEmailLoginOthers)
        password = findViewById(R.id.etPassLoginOthers)
        forgotPass = findViewById(R.id.tvForgotPassword)
        signUp = findViewById(R.id.tvSignup)
        loginBtn = findViewById(R.id.btnLoginOthers)
        firebaseAuth = FirebaseAuth.getInstance()

        signUp.setOnClickListener {
            startActivity(Intent(this, WhoAreYou::class.java))
        }

        loginBtn.setOnClickListener {
            loginUser()
        }

        forgotPass.setOnClickListener {
            if(TextUtils.isEmpty(email.text.toString())){
                Toast.makeText(this, "Enter the registered email id first.", Toast.LENGTH_SHORT).show()
            } else {
                val otp = (100000..999999).random().toString()
                Toast.makeText(this, "OTP is : $otp", Toast.LENGTH_SHORT).show()
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
                intent2.putExtra("otp", otp)
                startActivity(intent2)
            }
        }

    }

    private fun loginUser() {
        val emailText = email.text.toString().trim()
        val passwordText = password.text.toString().trim()

        if (TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passwordText)) {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Login failed!!")
                .setMessage("Fill all credentials first.")
                .setPositiveButton("Okay"){_,_-> }
                .create()
                .show()
        } else {
            val progressBar = ProgressDialog(this)
            progressBar.setMessage("Logging in..")
            progressBar.show()

            firebaseAuth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener { task ->
                progressBar.dismiss()
                if(task.isSuccessful) {
                    if(firebaseAuth.currentUser!!.isEmailVerified){
                        startActivity(Intent(this, HomeActivity::class.java))
                    } else {
                        Toast.makeText(this, "Email is not verified yet.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

}