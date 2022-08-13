package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.code.pragati.adapters.OnBoardingAdapter

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var viewPager : ViewPager2
    private lateinit var dots: LinearLayout
    private lateinit var sliderAdapter: OnBoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        viewPager = findViewById(R.id.slider)

        sliderAdapter = OnBoardingAdapter()
        viewPager.adapter = sliderAdapter



    }
}