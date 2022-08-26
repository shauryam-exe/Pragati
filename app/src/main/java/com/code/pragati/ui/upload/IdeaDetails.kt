package com.code.pragati.ui.upload

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.R
import com.code.pragati.model.Video
import com.code.pragati.model.VideoInfo

class IdeaDetails : AppCompatActivity() {

    private lateinit var continueBtn : AppCompatButton
    private lateinit var one: EditText
    private lateinit var two: EditText
    private lateinit var three: EditText
    private lateinit var four: EditText
    private lateinit var ask: EditText
    private lateinit var six: EditText
    private lateinit var seven: EditText
    private lateinit var eight: EditText
    private lateinit var nine: EditText
    private lateinit var ten: EditText
    private lateinit var eleven: EditText
    private lateinit var twelve: EditText
    private lateinit var thirteen: EditText
    private lateinit var fourteen: EditText
    private lateinit var fifteen: EditText
    private lateinit var sixteen: EditText
    private lateinit var seventeen: EditText


    private lateinit var a:String
    private lateinit var b:String
    private lateinit var c:String
    private lateinit var d:String
    private lateinit var askText:String
    private lateinit var f:String
    private lateinit var g:String
    private lateinit var h:String
    private lateinit var i:String
    private lateinit var j:String
    private lateinit var k:String
    private lateinit var l:String
    private lateinit var m:String
    private lateinit var n:String
    private lateinit var o:String
    private lateinit var p:String
    private lateinit var q:String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idea_details)


        continueBtn = findViewById(R.id.btnContinueIdea)
        one = findViewById(R.id.etDescribeFirstIdea)
        two = findViewById(R.id.etLiveSecondIdea)
        three = findViewById(R.id.etRaiseThirdIdea)
        four = findViewById(R.id.etMoneyFourIdea)
        ask = findViewById(R.id.etAmountFiveIdea)
        six = findViewById(R.id.etIntendSixIdea)
        seven = findViewById(R.id.etEnlistSevenIdea)
        eight = findViewById(R.id.etLongEightIdea)
        nine = findViewById(R.id.etPickNineIdea)
        ten = findViewById(R.id.etHowMoneyTenIdea)
        eleven = findViewById(R.id.etElevenText)
        twelve = findViewById(R.id.etTwelveText)
        thirteen = findViewById(R.id.etThirteenText)
        fourteen = findViewById(R.id.etFourteenText)
        fifteen = findViewById(R.id.etFifteenText)
        sixteen = findViewById(R.id.etSixteenText)
        seventeen = findViewById(R.id.etSeventeenText)


        continueBtn.setOnClickListener {
            getDetails()
            val videoInfo = intent.getParcelableExtra<VideoInfo>("videoInfo")
            videoInfo!!.a = a
            videoInfo.b = b
            videoInfo.c = c
            videoInfo.d = d
            videoInfo.ask = askText
            videoInfo.f = f
            videoInfo.g = g
            videoInfo.h = h
            videoInfo.i = i
            videoInfo.j = j
            videoInfo.k = k
            videoInfo.l = l
            videoInfo.m = m
            videoInfo.n = n
            videoInfo.o = o
            videoInfo.p = p
            videoInfo.q = q

            val video = intent.getParcelableExtra<Video>("video")

            val intent1 = Intent(this, UploadPitchFinal::class.java)
            intent1.putExtra("videoInfo",videoInfo)
            intent1.putExtra("video",video)
            startActivity(intent1)
        }

    }

    private fun getDetails() {
        a = one.text.toString()
        b = two.text.toString()
        c = three.text.toString()
        d = four.text.toString()
        askText = ask.text.toString()
        f = six.text.toString()
        g = seven.text.toString()
        h = eight.text.toString()
        i = nine.text.toString()
        j = ten.text.toString()
        k = eleven.text.toString()
        l = twelve.text.toString()
        m = thirteen.text.toString()
        n = fourteen.text.toString()
        o = fifteen.text.toString()
        p = sixteen.text.toString()
        q = seventeen.text.toString()
    }
}