package com.code.pragati.ui.upload

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.cloudinary.android.MediaManager
import com.code.pragati.R

class UploadYourPitch : AppCompatActivity() {

    private lateinit var applicantDetails: TextView
    private lateinit var founderDetails: TextView
    private lateinit var ideaDetails: TextView
    private lateinit var uploadPitchFinal: TextView
    private lateinit var shareIdea: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_your_pitch)
//
//        val config = mutableMapOf<String,String>()
//        config["dhbe64qfn"] = "myCloudName"
//        MediaManager.init(this)

        applicantDetails = findViewById(R.id.tvApplicantDetails)
        founderDetails = findViewById(R.id.tvFounderDetails)
        ideaDetails = findViewById(R.id.tvBusinessDetails)
        uploadPitchFinal = findViewById(R.id.tvVideoPresent)
        shareIdea = findViewById(R.id.btnShareIdea)

        applicantDetails.setOnClickListener {
            startActivity(Intent(this, ApplicantDetails::class.java))
        }
        shareIdea.isEnabled = false

//        founderDetails.setOnClickListener {
//            startActivity(Intent(this, FounderDetails::class.java))
//        }
//
//        ideaDetails.setOnClickListener {
//            startActivity(Intent(this, IdeaDetails::class.java))
//        }
//
//        uploadPitchFinal.setOnClickListener {
//            startActivity(Intent(this, UploadPitchFinal::class.java))
//        }

        val intent = intent.extras
        if (intent != null){
            applicantDetails.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_white_tick, 0)
            founderDetails.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_white_tick, 0)
            uploadPitchFinal.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_white_tick, 0)
            ideaDetails.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_white_tick, 0)
            shareIdea.visibility = View.VISIBLE

            shareIdea.isEnabled = true
            shareIdea.setOnClickListener {
                Toast.makeText(this,"Sharing your idea with the world",Toast.LENGTH_SHORT).show()
            }
        } else {
            shareIdea.visibility = View.GONE
        }

    }
}