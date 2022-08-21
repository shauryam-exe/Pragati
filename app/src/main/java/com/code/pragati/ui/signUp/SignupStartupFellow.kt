package com.code.pragati.ui.signUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.R

class SignupStartupFellow : AppCompatActivity() {

    private lateinit var continueBtn : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_startup_fellow)

        continueBtn = findViewById(R.id.btnContinueSignStartupFellow)

        continueBtn.setOnClickListener{
            startActivity(Intent(this, SignupFellow2::class.java))
        }

    }
}