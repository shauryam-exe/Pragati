package com.code.pragati.ui.upload

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.code.pragati.R

class UploadYourPitch : AppCompatActivity() {

    private lateinit var applicantDetails: TextView
    private lateinit var founderDetails: TextView
    private lateinit var ideaDetails: TextView
    private lateinit var uploadPitchFinal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_your_pitch)

        applicantDetails = findViewById(R.id.tvApplicantDetails)
        founderDetails = findViewById(R.id.tvFounderDetails)
        ideaDetails = findViewById(R.id.tvBusinessDetails)
        uploadPitchFinal = findViewById(R.id.tvVideoPresent)

        applicantDetails.setOnClickListener {
            startActivity(Intent(this, ApplicantDetails::class.java))
        }

        founderDetails.setOnClickListener {
            startActivity(Intent(this, FounderDetails::class.java))
        }

        ideaDetails.setOnClickListener {
            startActivity(Intent(this, IdeaDetails::class.java))
        }

        uploadPitchFinal.setOnClickListener {
            startActivity(Intent(this, UploadPitchFinal::class.java))
        }

    }
}