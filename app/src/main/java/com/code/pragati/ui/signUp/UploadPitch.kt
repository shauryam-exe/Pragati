package com.code.pragati.ui.signUp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.code.pragati.R

class UploadPitch : AppCompatActivity() {

    private lateinit var entrepreneur: CardView
    private lateinit var startupFellow: CardView
    private lateinit var student: CardView
    private lateinit var getStartedBtn: Button
    private lateinit var back: ImageView

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

    private lateinit var selectedRole: String

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

        back.setOnClickListener {
            onBackPressed()
        }
        entrepreneur.setOnClickListener {
            setButtonsGray()
            selectedRole = "entrepreneur"
            llEntrepreneur.setBackgroundColor(resources.getColor(R.color.primary))
            ivEntrepreneur.setImageResource(R.drawable.ic_investor_black_clicked)
            tvEntrepreneur.setTextColor(resources.getColor(R.color.white))

            getStartedBtn.isEnabled = true
            getStartedBtn.alpha = 1f
        }
        startupFellow.setOnClickListener {
            setButtonsGray()
            selectedRole = "startupFellow"
            llStartupFellow.setBackgroundColor(resources.getColor(R.color.primary))
            ivStartupFellow.setImageResource(R.drawable.ic_startup_clicked)
            tvStartupFellow.setTextColor(resources.getColor(R.color.white))

            getStartedBtn.isEnabled = true
            getStartedBtn.alpha = 1f
        }
        student.setOnClickListener {
            setButtonsGray()
            selectedRole = "student"
            llStudent.setBackgroundColor(resources.getColor(R.color.primary))
            ivStudent.setImageResource(R.drawable.ic_student_clicked)
            tvStudent.setTextColor(resources.getColor(R.color.white))

            getStartedBtn.isEnabled = true
            getStartedBtn.alpha = 1f
        }

        getStartedBtn.setOnClickListener {
            when(selectedRole) {
                "entrepreneur" -> {
                    startActivity(Intent(this, SignupEntrepreneur::class.java))
                }
                "startupFellow" -> {
                    startActivity(Intent(this, SignupStartupFellow::class.java))
                }
                "student" -> {
                    startActivity(Intent(this, SignupStudent::class.java))
                }
            }
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