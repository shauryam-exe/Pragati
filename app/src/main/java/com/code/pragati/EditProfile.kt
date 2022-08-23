package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class EditProfile : AppCompatActivity() {

    private lateinit var back : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        back = findViewById(R.id.ivBackEdit)

        back.setOnClickListener {
            onBackPressed()
        }

    }
}