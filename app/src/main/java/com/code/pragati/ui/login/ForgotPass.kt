package com.code.pragati.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.code.pragati.R
import com.code.pragati.model.UserType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var userType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        firebaseAuth = FirebaseAuth.getInstance()
        back = findViewById(R.id.ivBackForgot)
        correctOtp = findViewById(R.id.ivCorrectOTPForgot)
        incorrectOtp = findViewById(R.id.tvIncorrectOTPForgot)
        enterOtp = findViewById(R.id.etEnterOTPForgot)
        newPass = findViewById(R.id.etNewPassForgot)
        confirmPass = findViewById(R.id.etConfirmPassForgot)
        continueLogin = findViewById(R.id.btnContinueLoginForgot)
        newPassCard = findViewById(R.id.cardNewPassForgot)
        confirmPassCard = findViewById(R.id.cardConfirmPassForgot)

        correctOtp.visibility = View.INVISIBLE
        incorrectOtp.visibility = View.INVISIBLE

        back.setOnClickListener{
            onBackPressed()
        }

        continueLogin.setOnClickListener {
//            resetPassword()
            startActivity(Intent(this, LoginOthers::class.java))
        }

        val otp = intent.getStringExtra("otp")
        verifyOtp(otp!!)

    }

    private fun resetPassword() {

        val confirmPassText = confirmPass.text.toString().trim()
        val passwordText = newPass.text.toString().trim()

        if (TextUtils.isEmpty(passwordText) || TextUtils.isEmpty(confirmPassText)) {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Reset failed!!")
                .setMessage("Fill all credentials first.")
                .setPositiveButton("Okay"){_,_-> }
                .create()
                .show()
        } else {
            val progressBar = ProgressDialog(this)
            progressBar.setMessage("Resetting your password..")
            progressBar.show()

            //Yaha isliye nhi hoga as logged out hai, current user is null.
            firebaseAuth.currentUser!!.updatePassword(passwordText).addOnCompleteListener { task ->
                progressBar.dismiss()
                if(task.isSuccessful) {
                    updatePass(passwordText)
                    startActivity(Intent(this, LoginOthers::class.java))
                } else {
                    Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun updatePass(password : String) {
            FirebaseDatabase.getInstance().getReference("UsersID")
                .child(firebaseAuth.currentUser!!.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.getValue(UserType::class.java)
                        userType = user!!.Type
                        Log.d("user",userType)
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })

            FirebaseDatabase.getInstance().reference.child("Users").child(userType)
                .child(firebaseAuth.currentUser!!.uid).child("Password").setValue(password)
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