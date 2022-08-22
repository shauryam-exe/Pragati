package com.code.pragati.ui.upload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.code.pragati.R

class UploadPitchFinal : AppCompatActivity() {

    private lateinit var head : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_pitch_final)

        head = findViewById(R.id.tvCreateUpload)

        head.setOnClickListener {
            onBackPressed()
        }

    }
}