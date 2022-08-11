package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView

class ContactUs : AppCompatActivity() {

    private lateinit var email : TextView
    private lateinit var instagram : CardView
    private lateinit var linkedIn : CardView
    private lateinit var social : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        email = findViewById(R.id.tvContactViaMail)
        instagram = findViewById(R.id.cardContactViaInstagram)
        linkedIn = findViewById(R.id.cardContactViaLinkedIn)
        social = findViewById(R.id.cardContactViaSocial)

    }
}