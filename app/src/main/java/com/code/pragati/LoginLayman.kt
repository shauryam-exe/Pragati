package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView

class LoginLayman : AppCompatActivity() {

    private lateinit var number : EditText
    private lateinit var getOTP : Button
    private lateinit var google : CardView
    private lateinit var linkedIn : CardView
    private lateinit var social : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_leyman)

        number = findViewById(R.id.etNumberLogin)
        getOTP = findViewById(R.id.btnGetOTP)
        google = findViewById(R.id.cardLoginWithGoogle)
        linkedIn = findViewById(R.id.cardLoginWithLinkedIn)
        social = findViewById(R.id.cardLoginWithSocial)

    }
}