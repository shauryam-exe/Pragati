package com.code.pragati.ui.upload

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.HomeActivity
import com.code.pragati.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UploadPSFinal : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etDesc: EditText
    private lateinit var share: AppCompatButton
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uplaod_psfinal)

        etTitle = findViewById(R.id.etTitlePSFinal)
        etDesc = findViewById(R.id.etDescribePSFinal)
        share = findViewById(R.id.btnShareProblemPSFinal)
        firebaseAuth = FirebaseAuth.getInstance()

        share.setOnClickListener {
            addPS()
        }

    }

    private fun addPS() {

        val title = etTitle.text.toString().trim()
        val desc = etDesc.text.toString().trim()

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(desc)) {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Upload failed!!")
                .setMessage("Fill all credentials first.")
                .setPositiveButton("Okay") { _, _ -> }
                .create()
                .show()
        } else {

            val progressBar = ProgressDialog(this)
            progressBar.setMessage("Adding your problem statement...")
            progressBar.show()

            //Storing user's info
            val map = HashMap<String, Any>()
            map["Title"] = title
            map["Description"] = desc

            //Updating user's info to realtime database
            FirebaseDatabase.getInstance().reference.child("PS")
                .child(firebaseAuth.currentUser!!.uid).updateChildren(map)
                .addOnCompleteListener { task1 ->
                    progressBar.dismiss()
                    if (task1.isSuccessful) {
                        startActivity(Intent(this, HomeActivity::class.java))
                    } else {
                        Toast.makeText(
                            this,
                            task1.exception?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

}
