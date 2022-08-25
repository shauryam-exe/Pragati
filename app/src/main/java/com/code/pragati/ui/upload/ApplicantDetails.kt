package com.code.pragati.ui.upload

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.R
import com.code.pragati.model.Video
import com.code.pragati.model.VideoInfo

class ApplicantDetails : AppCompatActivity() {

    private lateinit var continueBtn : AppCompatButton
    private lateinit var applicantName: EditText
    private lateinit var ideaName: EditText
    private lateinit var websiteURL: EditText

    private lateinit var idea: String
    private lateinit var website: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applicant_details)

        continueBtn = findViewById(R.id.btnContinueApplicant)
        ideaName = findViewById(R.id.etIdeaApplicant)
        websiteURL = findViewById(R.id.etWesbiteURLApplicant)



        continueBtn.setOnClickListener {
            getDetails()
            val videoInfo = VideoInfo(website = website)
            val video = Video(name = idea)
            intent = Intent(this,IdeaDetails::class.java)
            intent.putExtra("videoInfo",videoInfo)
            intent.putExtra("video",video)
            startActivity(intent)
        }

    }

    private fun getDetails() {
        idea = ideaName.text.toString().trim()
        website = websiteURL.text.toString().trim()
    }
}