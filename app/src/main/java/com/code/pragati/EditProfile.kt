package com.code.pragati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.doOnTextChanged
import com.code.pragati.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EditProfile : AppCompatActivity() {

    private lateinit var back : ImageView
    private lateinit var enterPass : EditText
    private lateinit var correctPass : ImageView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var saveBtn : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        back = findViewById(R.id.ivBackEdit)
        enterPass = findViewById(R.id.etEnterCurrentPass)
        correctPass = findViewById(R.id.ivCorrectPass)
        saveBtn = findViewById(R.id.btnSaveNewDetails)
        firebaseAuth = FirebaseAuth.getInstance()

        back.setOnClickListener {
            onBackPressed()
        }

        correctPass.visibility = View.INVISIBLE

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
                    correctPass.visibility = View.VISIBLE
                    saveBtn.isEnabled = true
                    saveBtn.alpha = 1f
                } else {
                    correctPass.visibility = View.INVISIBLE
                    saveBtn.isEnabled = false
                    saveBtn.alpha = 0.3f
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

    }
}