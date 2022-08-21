package com.code.pragati.ui.signUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.FellowCredentials
import com.code.pragati.R

class SignupAngels : AppCompatActivity() {

    private lateinit var continueBtn : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_angels)

        continueBtn = findViewById(R.id.btnContinueSignAngels)

        continueBtn.setOnClickListener{
            startActivity(Intent(this, FellowCredentials::class.java))
        }

    }
}