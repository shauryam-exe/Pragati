package com.code.pragati

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.code.pragati.ui.login.LoginOthers


class ContactUs : AppCompatActivity() {

    private lateinit var email : TextView
    private lateinit var instagram : ImageButton
    private lateinit var linkedIn : ImageButton
    private lateinit var social : CardView
    private lateinit var back : ImageView
    private lateinit var proceedToLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        email = findViewById(R.id.tvContactViaMail)
        instagram = findViewById(R.id.ibContactViaInstagram)
        linkedIn = findViewById(R.id.ibContactViaLinkedIn)
        social = findViewById(R.id.cardContactViaSocial)
        back = findViewById(R.id.ivBackContactUs)
        proceedToLogin = findViewById(R.id.btnProceedToLogin)

        back.setOnClickListener{
            onBackPressed()
        }

        proceedToLogin.setOnClickListener {
            startActivity(Intent(this, LoginOthers::class.java))
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