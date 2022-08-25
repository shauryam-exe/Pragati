package com.code.pragati.ui.signUp

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.setMargins
import com.code.pragati.FellowCredentials
import com.code.pragati.R
import com.google.android.flexbox.FlexboxLayout

class SignupAngels : AppCompatActivity() {

    private lateinit var continueBtn : AppCompatButton
    private var tags = ArrayList<String>()
    private lateinit var flexbox: FlexboxLayout

    private val maxTags = 3
    private var tagCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_angels)

        continueBtn = findViewById(R.id.btnContinueSignAngels)
        flexbox = findViewById(R.id.flexBoxAngels)

        val textArray = arrayOf("tag1","sadasfasfafsfaf","tag3","asfasffsafasas","tag5","tag6","tag7","tag8","tag1","tag2","tag3","tag4","tag5","tag6","tag7","tag8","tag1","tag2","tag3","tag4","tag5","tag6","tag7","tag8","tag1","tag2","tag3","tag4","tag5","tag6","tag7","tag8")
        val params = FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
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

        continueBtn.setOnClickListener{
            startActivity(Intent(this, FellowCredentials::class.java))
        }

    }
}