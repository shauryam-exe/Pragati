package com.code.pragati.ui.signUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.FellowCredentials
import com.code.pragati.R

class SignupFellow2 : AppCompatActivity() {

    private lateinit var signUp : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_fellow2)

        signUp = findViewById(R.id.btnSignFellow2)

        signUp.setOnClickListener{
            startActivity(Intent(this, FellowCredentials::class.java))
        }

    }
}