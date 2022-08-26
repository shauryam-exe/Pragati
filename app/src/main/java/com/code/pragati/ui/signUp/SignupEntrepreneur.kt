package com.code.pragati.ui.signUp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.FellowCredentials
import com.code.pragati.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupEntrepreneur : AppCompatActivity() {

    private lateinit var continueBtn : AppCompatButton
    private lateinit var nameET : EditText
    private lateinit var emailET : EditText
    private lateinit var CINET : EditText
    private lateinit var passET : EditText
    private lateinit var confirmPassET : EditText
    private var isVerified : Boolean? = null
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_entrepreneur)

        nameET = findViewById(R.id.etFullNameEntrepreneur)
        emailET = findViewById(R.id.etEmailEntrepreneur)
        CINET = findViewById(R.id.etCINEntrepreneur)
        passET = findViewById(R.id.etSetPassEntre)
        confirmPassET = findViewById(R.id.etConfirmPassEntre)
        continueBtn = findViewById(R.id.btnContinueSignEntrepreneur)
        firebaseAuth = FirebaseAuth.getInstance()

        continueBtn.setOnClickListener{
            signUpEntrepreneur()
        }

    }


    private fun signUpEntrepreneur() {

        val name = nameET.text.toString().trim()
        val email = emailET.text.toString().trim()
        val cin = CINET.text.toString().trim()
        val setPass = passET.text.toString().trim()
        val confirmPass = confirmPassET.text.toString().trim()

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email)
            || TextUtils.isEmpty(setPass) || TextUtils.isEmpty(
                confirmPass
            )
        ) {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Signup failed!!")
                .setMessage("Fill all credentials first but CIN is optional")
                .setPositiveButton("Okay") { _, _ -> }
                .create()
                .show()
        } else {

            if (setPass == confirmPass) {

                val progressBar = ProgressDialog(this)
                progressBar.setMessage("Signing you up..")
                progressBar.show()

                firebaseAuth.createUserWithEmailAndPassword(email, setPass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            isVerified = !TextUtils.isEmpty(cin)

                            //Storing user's info
                            val map = HashMap<String, Any>()
                            map["Email"] = email
                            map["Name"] = name
                            map["CIN"] = cin
                            map["id"] = firebaseAuth.currentUser!!.uid
                            map["isVerified"] = isVerified.toString()
                            map["Password"] = setPass

                            val map2 = HashMap<String, Any>()
                            map2["id"] = firebaseAuth.currentUser!!.uid
                            map2["Type"] = "Entrepreneur"

                            //Updating user's info to realtime database
                            FirebaseDatabase.getInstance().reference.child("Users").child("Entrepreneur")
                                .child(firebaseAuth.currentUser!!.uid).updateChildren(map)
                                .addOnCompleteListener { task1 ->
                                    progressBar.dismiss()
                                    if (task1.isSuccessful) {
                                        sendVerificationMail()
                                        FirebaseDatabase.getInstance().reference.child("UsersID")
                                            .child(firebaseAuth.currentUser!!.uid).updateChildren(map2)
                                    } else {
                                        Toast.makeText(
                                            this,
                                            task1.exception?.message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        } else {
                            Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }

            } else {
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Signup failed!!")
                    .setMessage("Password didn't matched.")
                    .setPositiveButton("Okay") { _, _ -> }
                    .create()
                    .show()
            }

        }

    }

    private fun sendVerificationMail() {

        firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
            if (it.isSuccessful){
                startActivity(Intent(this, FellowCredentials::class.java))
            } else {
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }

    }
}