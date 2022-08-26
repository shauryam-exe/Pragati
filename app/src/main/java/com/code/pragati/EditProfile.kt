package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.FirebaseAuth

class EditProfile : AppCompatActivity() {

    private lateinit var back : ImageView
    private lateinit var enterPass : EditText
    private lateinit var correctPass : ImageView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        back = findViewById(R.id.ivBackEdit)
        enterPass = findViewById(R.id.etEnterCurrentPass)
        correctPass = findViewById(R.id.ivCorrectPass)
        firebaseAuth = FirebaseAuth.getInstance()

        back.setOnClickListener {
            onBackPressed()
        }

//        enterPass.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun afterTextChanged(p0: Editable?) {
//                if(p0?.length!! >= 6){
//                    if (p0.equals(otp)){
//                        newPass.isEnabled = true
//                        newPass.alpha = 1f
//                        confirmPass.alpha = 1f
//                        continueLogin.alpha = 1f
//                        confirmPass.isEnabled = true
//                        continueLogin.isEnabled = true
//                        correctOtp.visibility = View.VISIBLE
//                    } else {
//                        incorrectOtp.visibility = View.VISIBLE
//                    }
//                }
//            }
//        }

    }
}