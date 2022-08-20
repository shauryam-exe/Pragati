package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView

class UploadPitch : AppCompatActivity() {

    private lateinit var entrepreneur: CardView
    private lateinit var startupFellow: CardView
    private lateinit var student: CardView
    private lateinit var getStartedBtn: Button
    private lateinit var back : ImageView

    //For UI Effects...
    private lateinit var llEntrepreneur: LinearLayout
    private lateinit var llStartupFellow: LinearLayout
    private lateinit var llStudent: LinearLayout
    private lateinit var ivEntrepreneur: ImageView
    private lateinit var ivStartupFellow: ImageView
    private lateinit var ivStudent: ImageView
    private lateinit var tvEntrepreneur: TextView
    private lateinit var tvStartupFellow: TextView
    private lateinit var tvStudent: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_pitch)

        entrepreneur = findViewById(R.id.cardEntrepreneur)
        startupFellow = findViewById(R.id.cardStartupFellows)
        student = findViewById(R.id.cardStudent)
        getStartedBtn = findViewById(R.id.btnGetStartedPitch)

        llEntrepreneur = findViewById(R.id.llEntrepreneur)
        llStartupFellow = findViewById(R.id.llStartupFellows)
        llStudent = findViewById(R.id.llStudent)
        ivEntrepreneur = findViewById(R.id.ivEntrepreneur)
        ivStartupFellow = findViewById(R.id.ivStartupFellows)
        ivStudent = findViewById(R.id.ivStudent)
        tvEntrepreneur = findViewById(R.id.tvEntrepreneur)
        tvStartupFellow = findViewById(R.id.tvStartupFellows)
        tvStudent = findViewById(R.id.tvStudent)
        back = findViewById(R.id.ivBackPitch)

        back.setOnClickListener{
            onBackPressed()
        }
        entrepreneur.setOnClickListener {
            setButtonsGray()
            llEntrepreneur.setBackgroundColor(resources.getColor(R.color.primary))
            ivEntrepreneur.setImageResource(R.drawable.ic_investor_black_clicked)
            tvEntrepreneur.setTextColor(resources.getColor(R.color.white))

            getStartedBtn.isEnabled = true
            getStartedBtn.alpha = 1f
        }
        startupFellow.setOnClickListener {
            setButtonsGray()
            llStartupFellow.setBackgroundColor(resources.getColor(R.color.primary))
            ivStartupFellow.setImageResource(R.drawable.ic_startup_clicked)
            tvStartupFellow.setTextColor(resources.getColor(R.color.white))

            getStartedBtn.isEnabled = true
            getStartedBtn.alpha = 1f
        }
        student.setOnClickListener {
            setButtonsGray()
            llStudent.setBackgroundColor(resources.getColor(R.color.primary))
            ivStudent.setImageResource(R.drawable.ic_student_clicked)
            tvStudent.setTextColor(resources.getColor(R.color.white))

            getStartedBtn.isEnabled = true
            getStartedBtn.alpha = 1f
        }

        getStartedBtn.setOnClickListener {
            //TODO
        }
    }

    private fun setButtonsGray() {
        llEntrepreneur.setBackgroundColor(resources.getColor(R.color.white))
        ivEntrepreneur.setImageResource(R.drawable.ic_investor_black)
        tvEntrepreneur.setTextColor(resources.getColor(R.color.black))

        llStartupFellow.setBackgroundColor(resources.getColor(R.color.white))
        ivStartupFellow.setImageResource(R.drawable.ic_startup)
        tvStartupFellow.setTextColor(resources.getColor(R.color.black))

        llStudent.setBackgroundColor(resources.getColor(R.color.white))
        ivStudent.setImageResource(R.drawable.ic_student)
        tvStudent.setTextColor(resources.getColor(R.color.black))
    }
}