package com.code.pragati

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.code.pragati.fragments.Inbox

class HomeActivity : AppCompatActivity() {

    private lateinit var fragment : FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fragment = findViewById(R.id.container)

        supportFragmentManager.beginTransaction().replace(R.id.container, Inbox()).commit()

    }
}