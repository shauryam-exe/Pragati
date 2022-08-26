package com.code.pragati.ui.upload

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.code.pragati.HomeActivity
import com.code.pragati.R
import com.code.pragati.model.*
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage


class UploadYourPitch : AppCompatActivity() {

    private lateinit var applicantDetails: TextView
    private lateinit var founderDetails: TextView
    private lateinit var ideaDetails: TextView
    private lateinit var uploadPitchFinal: TextView
    private lateinit var shareIdea: AppCompatButton
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var type: String
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_your_pitch)

        firebaseAuth = FirebaseAuth.getInstance()
        applicantDetails = findViewById(R.id.tvApplicantDetails)
        founderDetails = findViewById(R.id.tvFounderDetails)
        ideaDetails = findViewById(R.id.tvBusinessDetails)
        uploadPitchFinal = findViewById(R.id.tvVideoPresent)
        shareIdea = findViewById(R.id.btnShareIdea)

        type = intent.getStringExtra("userType").toString()


        applicantDetails.setOnClickListener {
            val intent = Intent(this,ApplicantDetails::class.java)
            intent.putExtra("type",type)
            startActivity(intent)
        }
        shareIdea.isEnabled = false

        val intent1 = intent.extras
        if (intent1 != null) {
            applicantDetails.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_white_tick,
                0
            )
            founderDetails.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_white_tick,
                0
            )
            uploadPitchFinal.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_white_tick,
                0
            )
            ideaDetails.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_white_tick, 0)
            shareIdea.visibility = View.VISIBLE

            val video = intent.getParcelableExtra<Video>("video")
            val videoInfo = intent.getParcelableExtra<VideoInfo>("videoInfo")


            shareIdea.isEnabled = true
            shareIdea.setOnClickListener {
                Toast.makeText(this, "Sharing your idea with the world", Toast.LENGTH_SHORT).show()
                uploadVideo(video = video!!)
            }
        } else {
            shareIdea.visibility = View.GONE
        }



    }

    private fun uploadVideo(video: Video) {

        val timestamp = System.currentTimeMillis()

        val filePath = "Videos/video_$timestamp"


        val reference = FirebaseStorage.getInstance()
            .reference.child("/videos/" + System.currentTimeMillis() + "." + getfiletype(video.uri))
//        reference.putFile(video.uri!!).addOnSuccessListener {
        reference.putFile(video.uri!!).addOnSuccessListener { taskSnapshot ->
            val uriTask: Task<Uri> = taskSnapshot.storage.downloadUrl
            uriTask.addOnCompleteListener {
                val downloadUri: String = it.result.toString()

                val reference1 = FirebaseDatabase.getInstance().reference.child("Video")
                val map: HashMap<String, Any> = HashMap()
                map["uri"] = downloadUri
                map["uid"] = firebaseAuth.currentUser?.uid.toString()
                map["ideaName"] = video.ideaName
                map["likes"] = video.likes.toString()
                map["name"] = video.name
                map["type"] = video.type
                reference1.child("" + System.currentTimeMillis()).setValue(map)
                // Video uploaded successfully
                // Dismiss dialog
                Toast.makeText(this@UploadYourPitch, "Video Uploaded!!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@UploadYourPitch,HomeActivity::class.java))
            }
//                while (!uriTask.isSuccessful);
            // get the link of video


        }.addOnFailureListener { e -> // Error, Image not uploaded
            Toast.makeText(this@UploadYourPitch, "Failed " + e.message, Toast.LENGTH_SHORT)
                .show()
        }.addOnProgressListener { taskSnapshot ->
            // Progress Listener for loading
            // percentage on the dialog box
            // show the progress bar
            val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
        }
//        }
    }

    private fun getfiletype(uri: Uri?): String {
        val r = contentResolver
        // get the file type ,in this case its mp4
        // get the file type ,in this case its mp4
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(r.getType(uri!!))!!
    }
}