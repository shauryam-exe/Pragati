package com.code.pragati.ui.upload

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.code.pragati.R
import com.code.pragati.model.Video

class UploadPitchFinal : AppCompatActivity() {

    private lateinit var head: TextView
    private lateinit var cardUpload: CardView
    private lateinit var uploadVideoView: VideoView
    private lateinit var continueBtn: Button
    private lateinit var video: Video

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_pitch_final)

        head = findViewById(R.id.tvCreateUpload)
        cardUpload = findViewById(R.id.cardUploadPitch)
        uploadVideoView = findViewById(R.id.videoView)
        continueBtn = findViewById(R.id.btnContinueUpload)

        continueBtn.setOnClickListener {
            val intent = Intent(this, UploadYourPitch::class.java)
            intent.putExtra("a", video)
            startActivity(intent)
        }

        cardUpload.setOnClickListener {
            videoPickDialog()
        }

    }

    private fun videoPickDialog() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "video/*"
        startActivityForResult(intent, 1)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val videoUri = data?.data
            video = Video(videoUri!!, null)
//            video.uri = videoUri!!

            cardUpload.visibility = View.INVISIBLE

            uploadVideoView.visibility = View.VISIBLE
            uploadVideoView.setVideoURI(videoUri)
            uploadVideoView.start()
        }
    }
}