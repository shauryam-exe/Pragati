package com.code.pragati

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.code.pragati.fragments.Inbox
import com.code.pragati.fragments.NotificationFragment
import com.code.pragati.fragments.Profile
import com.code.pragati.fragments.Search
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
                    selectorFragment = Search()
                }
                R.id.message -> {
                    selectorFragment = Inbox()
                }
                R.id.pitch -> {
                    Toast.makeText(this, "pitch hai", Toast.LENGTH_SHORT).show()
                    selectorFragment = Inbox()
//                    TODO("change")
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
        supportFragmentManager.beginTransaction().replace(R.id.container, Search()).commit()

    }
}