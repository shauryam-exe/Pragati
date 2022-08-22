package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ChatInside : AppCompatActivity() {

    private lateinit var save : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_inside)

        save = findViewById(R.id.ivSaveChat)

        save.setOnClickListener {
            save.setImageResource(R.drawable.ic_saved_primary)
        }

    }
}