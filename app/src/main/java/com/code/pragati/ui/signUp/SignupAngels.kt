package com.code.pragati.ui.signUp

import android.app.ActionBar
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.setMargins
import com.code.pragati.FellowCredentials
import com.code.pragati.R
import com.google.android.flexbox.FlexboxLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupAngels : AppCompatActivity() {

    private lateinit var continueBtn : AppCompatButton
    private var tags = ArrayList<String>()
    private lateinit var flexbox: FlexboxLayout
    private lateinit var emailET : EditText
    private lateinit var nameET : EditText
    private lateinit var regET : EditText
    private lateinit var setPassET : EditText
    private lateinit var confirmPassET : EditText
    private lateinit var firebaseAuth: FirebaseAuth

    private val maxTags = 3
    private var tagCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_angels)

        continueBtn = findViewById(R.id.btnContinueSignAngels)
        flexbox = findViewById(R.id.flexBoxAngels)
        regET = findViewById(R.id.etRegAngels)
        emailET = findViewById(R.id.etEmailAngels)
        nameET = findViewById(R.id.etFullNameAngels)
        setPassET = findViewById(R.id.etSetPassAngels)
        confirmPassET = findViewById(R.id.etConfirmPassAngels)
        firebaseAuth = FirebaseAuth.getInstance()

        val textArray = arrayOf("tag1","sadasfasfafsfaf","tag3","asfasffsafasas","tag5","tag6","tag7","tag8","tag1","tag2","tag3","tag4","tag5","tag6","tag7","tag8","tag1","tag2","tag3","tag4","tag5","tag6","tag7","tag8","tag1","tag2","tag3","tag4","tag5","tag6","tag7","tag8")
        val params = FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
        params.setMargins(16)

        for (i in textArray.indices) {
            val textView = TextView(this)
            textView.id = i
            textView.text = textArray[i]
            textView.setPadding(20,10,20,10)
            textView.layoutParams = params
            textView.setBackgroundResource(R.drawable.custom_tag_button)
            textView.textSize = 18f
            textView.setTextColor(getColor(R.color.white))
            textView.setOnClickListener {
                if (!textView.isSelected && tagCount < maxTags) {
                    textView.setBackgroundResource(R.drawable.tag_selected)
                    textView.setTextColor(getColor(R.color.primary))
                    textView.isSelected = true
                    tags.add(textView.text.toString())
                    tagCount++
                } else if (textView.isSelected){
                    textView.setBackgroundResource(R.drawable.custom_tag_button)
                    textView.setTextColor(getColor(R.color.white))
                    textView.isSelected = false
                    tags.remove(textView.text.toString())
                    tagCount--
                }
                Log.d("check tags",tags.toString())
            }
            flexbox.addView(textView)
        }

        continueBtn.setOnClickListener{
            signUpForAngels()
        }

    }

    private fun signUpForAngels() {

        val name = nameET.text.toString().trim()
        val email = emailET.text.toString().trim()
        val regNo = regET.text.toString().trim()
        val setPass = setPassET.text.toString().trim()
        val confirmPass = confirmPassET.text.toString().trim()

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(regNo)
            || TextUtils.isEmpty(setPass) || TextUtils.isEmpty(
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
                            map["Reg"] = regNo
                            map["id"] = firebaseAuth.currentUser!!.uid
                            map["Password"] = setPass

                            val map2 = HashMap<String, Any>()
                            map2["id"] = firebaseAuth.currentUser!!.uid
                            map2["Type"] = "Angel"

                            //Updating user's info to realtime database
                            FirebaseDatabase.getInstance().reference.child("Users").child("Angel")
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