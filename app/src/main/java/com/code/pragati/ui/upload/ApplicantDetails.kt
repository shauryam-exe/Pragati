package com.code.pragati.ui.upload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.code.pragati.R

class ApplicantDetails : AppCompatActivity() {

    private lateinit var head : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applicant_details)

        head = findViewById(R.id.tvCreateApplicant)

        head.setOnClickListener {
            onBackPressed()
        }

    }
}