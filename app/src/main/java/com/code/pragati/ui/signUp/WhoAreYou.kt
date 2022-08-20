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

class WhoAreYou : AppCompatActivity() {

    private lateinit var invest: CardView
    private lateinit var startupPitch: CardView
    private lateinit var explore: CardView
    private lateinit var uploadProblemStatement: CardView
    private lateinit var continueBtn: Button

    //For UI Effects...
    private lateinit var llRupees: LinearLayout
    private lateinit var llVideo: LinearLayout
    private lateinit var llSearch: LinearLayout
    private lateinit var llNote: LinearLayout
    private lateinit var ivRupees: ImageView
    private lateinit var ivVideo: ImageView
    private lateinit var ivSearch: ImageView
    private lateinit var ivNote: ImageView
    private lateinit var tvRupees: TextView
    private lateinit var tvVideo: TextView
    private lateinit var tvSearch: TextView
    private lateinit var tvNote: TextView

    private var selectedRole: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_who_are_you)

        invest = findViewById(R.id.cardInvest)
        startupPitch = findViewById(R.id.cardStartupPitch)
        explore = findViewById(R.id.cardExplore)
        uploadProblemStatement = findViewById(R.id.cardUploadProblemStatement)
        continueBtn = findViewById(R.id.btnContinueWhoAreYou)

        llRupees = findViewById(R.id.llRupees)
        llVideo = findViewById(R.id.llVideo)
        llSearch = findViewById(R.id.llSearch)
        llNote = findViewById(R.id.llNote)
        ivRupees = findViewById(R.id.ivRupees)
        ivVideo = findViewById(R.id.ivVideo)
        ivSearch = findViewById(R.id.ivSearch)
        ivNote = findViewById(R.id.ivNote)
        tvRupees = findViewById(R.id.tvRupees)
        tvVideo = findViewById(R.id.tvVideo)
        tvSearch = findViewById(R.id.tvSearch)
        tvNote = findViewById(R.id.tvNote)

        continueBtn.alpha = 0.5f

        invest.setOnClickListener {
            setButtonsGray()
                llRupees.setBackgroundColor(resources.getColor(R.color.primary))
                ivRupees.setImageResource(R.drawable.ic_rupees_clicked)
                tvRupees.setTextColor(resources.getColor(R.color.white))
                selectedRole = "invest"

            continueBtn.isEnabled = true
            continueBtn.alpha = 1f
        }
        startupPitch.setOnClickListener {
            setButtonsGray()
                llVideo.setBackgroundColor(resources.getColor(R.color.primary))
                ivVideo.setImageResource(R.drawable.ic_video_clicked)
                tvVideo.setTextColor(resources.getColor(R.color.white))
                selectedRole = "startup"

            continueBtn.isEnabled = true
            continueBtn.alpha = 1f
        }
        explore.setOnClickListener {
            setButtonsGray()
                llSearch.setBackgroundColor(resources.getColor(R.color.primary))
                ivSearch.setImageResource(R.drawable.ic_search_clicked)
                tvSearch.setTextColor(resources.getColor(R.color.white))
                selectedRole = "explore"

            continueBtn.isEnabled = true
            continueBtn.alpha = 1f
        }
        uploadProblemStatement.setOnClickListener {
            setButtonsGray()
                llNote.setBackgroundColor(resources.getColor(R.color.primary))
                ivNote.setImageResource(R.drawable.ic_note_clicked)
                tvNote.setTextColor(resources.getColor(R.color.white))
                selectedRole = "problem"

            continueBtn.isEnabled = true
            continueBtn.alpha = 1f
        }

        continueBtn.setOnClickListener {
            when(selectedRole) {
                "invest" -> startActivity(Intent(this, Invest::class.java))
                "startup" -> startActivity(Intent(this, UploadPitch::class.java))
                "explore" -> startActivity(Intent(this, LoginLayman::class.java))
                "problem" -> startActivity(Intent(this, ProblemStatementActivity::class.java))
            }
        }

    }



    private fun setButtonsGray() {
        llRupees.setBackgroundColor(resources.getColor(R.color.white))
        ivRupees.setImageResource(R.drawable.ic_rupees)
        tvRupees.setTextColor(resources.getColor(R.color.black))
        invest.isSelected = false

        llVideo.setBackgroundColor(resources.getColor(R.color.white))
        ivVideo.setImageResource(R.drawable.ic_video)
        tvVideo.setTextColor(resources.getColor(R.color.black))
        startupPitch.isSelected = false

        llSearch.setBackgroundColor(resources.getColor(R.color.white))
        ivSearch.setImageResource(R.drawable.ic_search)
        tvSearch.setTextColor(resources.getColor(R.color.black))
        explore.isSelected = false

        llNote.setBackgroundColor(resources.getColor(R.color.white))
        ivNote.setImageResource(R.drawable.ic_note)
        tvNote.setTextColor(resources.getColor(R.color.black))
        uploadProblemStatement.isSelected = false
    }
}