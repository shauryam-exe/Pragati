package com.code.pragati

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.code.pragati.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private var selectorFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.itemIconTintList = null
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    selectorFragment = HomeFragment()
                }
                R.id.message -> {
                    selectorFragment = Inbox()
                }
                R.id.pitch -> {
                    selectorFragment = PitchFragment()
                }
                R.id.notification -> {
                    selectorFragment = NotificationFragment()
                }
                R.id.profile -> {
                    selectorFragment = Profile()
                }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, selectorFragment!!).commit()

            return@setOnNavigationItemSelectedListener true
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()

    }
}