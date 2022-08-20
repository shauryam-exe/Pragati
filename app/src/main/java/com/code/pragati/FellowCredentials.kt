package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class FellowCredentials : AppCompatActivity() {

    private lateinit var email : TextView
    private lateinit var instagram : CardView
    private lateinit var linkedIn : CardView
    private lateinit var social : CardView
    private lateinit var back : ImageView
    private lateinit var proceedToLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fellow_credentials)

        email = findViewById(R.id.tvContactViaMailFellow)
        instagram = findViewById(R.id.cardContactViaInstagramFellow)
        linkedIn = findViewById(R.id.cardContactViaLinkedInFellow)
        social = findViewById(R.id.cardContactViaSocialFellow)
        back = findViewById(R.id.ivBackFellow)
        proceedToLogin = findViewById(R.id.btnProceedToLoginFellow)

        back.setOnClickListener{
            onBackPressed()
        }

    }
}