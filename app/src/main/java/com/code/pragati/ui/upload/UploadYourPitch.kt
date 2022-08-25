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
import androidx.core.net.toUri
import com.code.pragati.R
import com.code.pragati.model.Video
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


class UploadYourPitch : AppCompatActivity() {

    private lateinit var applicantDetails: TextView
    private lateinit var founderDetails: TextView
    private lateinit var ideaDetails: TextView
    private lateinit var uploadPitchFinal: TextView
    private lateinit var shareIdea: AppCompatButton

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_your_pitch)
//
//        val config = mutableMapOf<String,String>()
//        config["dhbe64qfn"] = "myCloudName"
//        MediaManager.init(this)

        applicantDetails = findViewById(R.id.tvApplicantDetails)
        founderDetails = findViewById(R.id.tvFounderDetails)
        ideaDetails = findViewById(R.id.tvBusinessDetails)
        uploadPitchFinal = findViewById(R.id.tvVideoPresent)
        shareIdea = findViewById(R.id.btnShareIdea)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Video Uploading")
        progressDialog.setCanceledOnTouchOutside(false)

        applicantDetails.setOnClickListener {
            startActivity(Intent(this, ApplicantDetails::class.java))
        }
        shareIdea.isEnabled = false

//        founderDetails.setOnClickListener {
//            startActivity(Intent(this, FounderDetails::class.java))
//        }
//
//        ideaDetails.setOnClickListener {
//            startActivity(Intent(this, IdeaDetails::class.java))
//        }
//
//        uploadPitchFinal.setOnClickListener {
//            startActivity(Intent(this, UploadPitchFinal::class.java))
//        }

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
        progressDialog.show()

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
                    map["ask"] = "ksdhvjsgd"
                    map["name"] = "dnfbebgfruygerugfuyegu"
                    reference1.child("" + System.currentTimeMillis()).setValue(map)
                    // Video uploaded successfully
                    // Dismiss dialog
                    progressDialog.dismiss()
                    Toast.makeText(this@UploadYourPitch, "Video Uploaded!!", Toast.LENGTH_SHORT).show()
                }
//                while (!uriTask.isSuccessful);
                // get the link of video


            }.addOnFailureListener { e -> // Error, Image not uploaded
                progressDialog.dismiss()
                Toast.makeText(this@UploadYourPitch, "Failed " + e.message, Toast.LENGTH_SHORT)
                    .show()
            }.addOnProgressListener { taskSnapshot ->
                // Progress Listener for loading
                // percentage on the dialog box
                // show the progress bar
                val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
                progressDialog.dismiss()
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