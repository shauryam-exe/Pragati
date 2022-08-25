package com.code.pragati.ui.signUp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.FellowCredentials
import com.code.pragati.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupStudent : AppCompatActivity() {

    private lateinit var continueBtn: AppCompatButton
    private lateinit var nameET: EditText
    private lateinit var emailET: EditText
    private lateinit var dobET: EditText
    private lateinit var phoneET: EditText
    private lateinit var setPassET: EditText
    private lateinit var confirmPassET: EditText
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_student)

        continueBtn = findViewById(R.id.btnContinueSignStudent)
        nameET = findViewById(R.id.etFullNameStudent)
        emailET = findViewById(R.id.etEmailStudent)
        dobET = findViewById(R.id.etDOBStudent)
        phoneET = findViewById(R.id.etPhoneStudent)
        setPassET = findViewById(R.id.etSetPassStudent)
        confirmPassET = findViewById(R.id.etConfirmPassStudent)
        firebaseAuth = FirebaseAuth.getInstance()

        continueBtn.setOnClickListener {

            createStudent()
        }

    }

    private fun createStudent() {

        val name = nameET.text.toString().trim()
        val email = emailET.text.toString().trim()
        val dob = dobET.text.toString().trim()
        val phone = phoneET.text.toString().trim()
        val setPass = setPassET.text.toString().trim()
        val confirmPass = confirmPassET.text.toString().trim()

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(dob)
            || TextUtils.isEmpty(phone) || TextUtils.isEmpty(setPass) || TextUtils.isEmpty(
                confirmPass
            )
        ) {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Signup failed!!")
                .setMessage("Fill all credentials first.")
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

                            //Storing user's info
                            val map = HashMap<String, Any>()
                            map["Email"] = email
                            map["Name"] = name
                            map["DOB"] = dob
                            map["id"] = firebaseAuth.currentUser!!.uid
                            map["Phone"] = phone
                            map["Password"] = setPass

                            //Updating user's info to realtime database
                            FirebaseDatabase.getInstance().reference.child("Users").child("Student")
                                .child(firebaseAuth.currentUser!!.uid).updateChildren(map)
                                .addOnCompleteListener { task1 ->
                                    progressBar.dismiss()
                                    if (task1.isSuccessful) {
                                        Toast.makeText(this, "Hogya bhau", Toast.LENGTH_SHORT).show()
                                        sendVerificationMail()

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