package com.code.pragati.ui.upload

import android.app.ActionBar
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.marginEnd
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.code.pragati.R
import com.code.pragati.model.Video
import com.code.pragati.model.VideoInfo
import com.google.android.flexbox.FlexboxLayout

class UploadPitchFinal : AppCompatActivity() {

    private lateinit var head: TextView
    private lateinit var cardUpload: CardView
    private lateinit var uploadVideoView: VideoView
    private lateinit var continueBtn: Button
    private lateinit var flexbox: FlexboxLayout

    private var tags = ArrayList<String>()

    private val maxTags = 3
    private var tagCount = 0

    var videoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_pitch_final)


        head = findViewById(R.id.tvCreateUpload)
        cardUpload = findViewById(R.id.cardUploadPitch)
        uploadVideoView = findViewById(R.id.videoView)
        continueBtn = findViewById(R.id.btnContinueUpload)
        flexbox = findViewById(R.id.tagsFlexbox)

        val textArray = arrayOf("tag1","sadasfasfafsfaf","tag3","asfasffsafasas","tag5","tag6","tag7","tag8","tag1","tag2","tag3","tag4","tag5","tag6","tag7","tag8","tag1","tag2","tag3","tag4","tag5","tag6","tag7","tag8","tag1","tag2","tag3","tag4","tag5","tag6","tag7","tag8")
        val params = FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT)
        params.setMargins(16)

        for (i in textArray.indices) {
            val textView = TextView(this)
            textView.id = i
            textView.text = textArray[i]
            textView.setPadding(20,10,20,10)
            textView.layoutParams = params
            textView.setBackgroundResource(R.drawable.custom_tag_button)
            textView.textSize = 18f
            textView.setTextColor(getColor(R.color.white))
            textView.setOnClickListener {
                if (!textView.isSelected && tagCount < maxTags) {
                    textView.setBackgroundResource(R.drawable.tag_selected)
                    textView.setTextColor(getColor(R.color.primary))
                    textView.isSelected = true
                    tags.add(textView.text.toString())
                    tagCount++
                } else if (textView.isSelected){
                    textView.setBackgroundResource(R.drawable.custom_tag_button)
                    textView.setTextColor(getColor(R.color.white))
                    textView.isSelected = false
                    tags.remove(textView.text.toString())
                    tagCount--
                }
                Log.d("check tags",tags.toString())
            }
            flexbox.addView(textView)
        }



        continueBtn.setOnClickListener {
            val videoInfo = intent.getParcelableExtra<VideoInfo>("videoInfo")
            val video = intent.getParcelableExtra<Video>("video")
            video!!.uri = videoUri
            val intent = Intent(this, UploadYourPitch::class.java)
            intent.putExtra("videoInfo",videoInfo)
            intent.putExtra("video",video)
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
            videoUri = data?.data

            cardUpload.visibility = View.INVISIBLE

            uploadVideoView.visibility = View.VISIBLE
            uploadVideoView.setVideoURI(videoUri)
            uploadVideoView.start()
        }
    }
}