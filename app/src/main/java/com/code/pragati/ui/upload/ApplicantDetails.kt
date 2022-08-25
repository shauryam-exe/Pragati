package com.code.pragati.ui.upload

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.R

class ApplicantDetails : AppCompatActivity() {

    private lateinit var continueBtn : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applicant_details)

        continueBtn = findViewById(R.id.btnContinueApplicant)

        continueBtn.setOnClickListener {
            startActivity(Intent(this, IdeaDetails::class.java))
        }

    }
}