package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class WhoAreYou : AppCompatActivity() {

    private lateinit var invest : CardView
    private lateinit var startupPitch : CardView
    private lateinit var searchStartupPitch : CardView
    private lateinit var uploadProblemStatement : CardView
    private lateinit var continueBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_who_are_you)

        invest = findViewById(R.id.cardInvest)
        startupPitch = findViewById(R.id.cardStartupPitch)
        searchStartupPitch = findViewById(R.id.cardSearchStartupPitch)
        uploadProblemStatement = findViewById(R.id.cardUploadProblemStatement)
        continueBtn = findViewById(R.id.btnContinueWhoAreYou)

    }
}