package com.code.pragati

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

        instagram.setOnClickListener {
            openApp(Uri.parse("https://www.instagram.com/axtbansal/"))
        }

        social.setOnClickListener {
            openApp(Uri.parse("https://www.twitter.com/axtbansal/"))
        }

        linkedIn.setOnClickListener {
            openApp(Uri.parse("https://www.linkedin.com/in/axtbansal/"))
        }

        email.setOnClickListener {
            openApp(Uri.parse("mailto:" + email.text))
        }
    }

    //To open a certain app on click.
    private fun openApp(uri: Uri){
        val intent =
            Intent(Intent.ACTION_VIEW, uri)
        //            intent.setPackage("com.instagram.android")
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}