package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView

class Invest : AppCompatActivity() {

    private lateinit var angelInvestor: CardView
    private lateinit var capitalist: CardView
    private lateinit var getStartedBtn: Button
    private lateinit var back : ImageView

    //For UI Effects...
    private lateinit var llAngel: LinearLayout
    private lateinit var llCapitalist: LinearLayout
    private lateinit var ivAngel: ImageView
    private lateinit var ivCapitalist: ImageView
    private lateinit var tvAngel: TextView
    private lateinit var tvCapitalist: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invest)

        angelInvestor = findViewById(R.id.cardAngelInvestor)
        capitalist = findViewById(R.id.cardCapitalist)
        getStartedBtn = findViewById(R.id.btnGetStartedInvest)

        llAngel = findViewById(R.id.llAngel)
        llCapitalist = findViewById(R.id.llCapitalist)
        ivAngel = findViewById(R.id.ivAngel)
        ivCapitalist = findViewById(R.id.ivCapitalist)
        tvAngel = findViewById(R.id.tvAngel)
        tvCapitalist = findViewById(R.id.tvCapitalist)
        back = findViewById(R.id.ivBackInvest)

        back.setOnClickListener{
            onBackPressed()
        }
        angelInvestor.setOnClickListener {
            setGrayButton()
            llAngel.setBackgroundColor(resources.getColor(R.color.primary))
            ivAngel.setImageResource(R.drawable.ic_investor_clicked)
            tvAngel.setTextColor(resources.getColor(R.color.white))

            getStartedBtn.isEnabled = true
            getStartedBtn.alpha = 1f
        }
        capitalist.setOnClickListener {
            setGrayButton()
            llCapitalist.setBackgroundColor(resources.getColor(R.color.primary))
            ivCapitalist.setImageResource(R.drawable.ic_investor_black_clicked)
            tvCapitalist.setTextColor(resources.getColor(R.color.white))

            getStartedBtn.isEnabled = true
            getStartedBtn.alpha = 1f
        }

        getStartedBtn.setOnClickListener {
            //TODO
        }

    }

    private fun setGrayButton() {
        llAngel.setBackgroundColor(resources.getColor(R.color.white))
        ivAngel.setImageResource(R.drawable.ic_investor)
        tvAngel.setTextColor(resources.getColor(R.color.black))

        llCapitalist.setBackgroundColor(resources.getColor(R.color.white))
        ivCapitalist.setImageResource(R.drawable.ic_investor_black)
        tvCapitalist.setTextColor(resources.getColor(R.color.black))
    }
}