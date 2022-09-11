package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.widget.doOnTextChanged
import com.code.pragati.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import de.hdodenhof.circleimageview.CircleImageView

class EditProfile : AppCompatActivity() {

    private lateinit var back : ImageView
    private lateinit var enterPass : EditText
    private lateinit var usernameEdit : EditText
    private lateinit var emailEdit : EditText
    private lateinit var newPassEdit : EditText
    private lateinit var usernameCard : CardView
    private lateinit var emailCard : CardView
    private lateinit var newPassCard : CardView
    private lateinit var correctPass : ImageView
    private lateinit var profileImage : CircleImageView
    private lateinit var editImage : ImageButton
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var saveBtn : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        back = findViewById(R.id.ivBackEdit)
        enterPass = findViewById(R.id.etEnterCurrentPass)
        correctPass = findViewById(R.id.ivCorrectPass)
        saveBtn = findViewById(R.id.btnSaveNewDetails)
        usernameEdit = findViewById(R.id.etUsernameEdit)
        emailEdit = findViewById(R.id.etEmailEdit)
        newPassEdit = findViewById(R.id.etNewPassEdit)
        usernameCard = findViewById(R.id.cardUsername)
        emailCard = findViewById(R.id.cardEmailEdit)
        newPassCard = findViewById(R.id.cardNewPassEdit)
        profileImage = findViewById(R.id.civProfile)
        editImage = findViewById(R.id.ibChangeProfile)
        firebaseAuth = FirebaseAuth.getInstance()

        back.setOnClickListener {
            onBackPressed()
        }

        val map = HashMap<String, String>()

        FirebaseDatabase.getInstance().reference.child("Users").child(intent.getStringExtra("type").toString())
            .child(firebaseAuth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user : User? = snapshot.getValue(User::class.java)
                    map["pass"] = user!!.Password
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

        enterPass.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.contentEquals(map["pass"], false)) {
                    enableFeatures()
                } else {
                    disableFeatures()
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

    }

    private fun enableFeatures(){
        correctPass.visibility = View.VISIBLE
        saveBtn.isEnabled = true
        usernameEdit.isEnabled = true
        emailEdit.isEnabled = true
        newPassEdit.isEnabled = true
        saveBtn.alpha = 1f
        profileImage.alpha = 1f
        editImage.alpha = 1f
        usernameCard.alpha = 1f
        emailCard.alpha = 1f
        newPassCard.alpha = 1f
    }

    private fun disableFeatures(){
        correctPass.visibility = View.INVISIBLE
        saveBtn.isEnabled = false
        usernameEdit.isEnabled = false
        emailEdit.isEnabled = false
        newPassEdit.isEnabled = false
        saveBtn.alpha = 0.3f
        profileImage.alpha = 0.4f
        editImage.alpha = 0.4f
        usernameCard.alpha = 0.4f
        emailCard.alpha = 0.4f
        newPassCard.alpha = 0.4f
    }

}