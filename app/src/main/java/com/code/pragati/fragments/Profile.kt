package com.code.pragati.fragments

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.core.view.setMargins
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.code.pragati.EditProfile
import com.code.pragati.R
import com.code.pragati.model.User
import com.code.pragati.model.UserType
import com.code.pragati.model.VideoItem
import com.code.pragati.ui.login.LoginOthers
import com.code.pragati.ui.upload.UploadPSFinal
import com.code.pragati.ui.upload.UploadYourPitch
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Profile : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawer: ImageView
    private lateinit var navView: NavigationView

    private lateinit var addFab: FloatingActionButton
    private lateinit var uploadPS: FloatingActionButton
    private lateinit var uploadPitch: FloatingActionButton
    private lateinit var linearLayout: LinearLayout
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var name: TextView
    private lateinit var type: TextView
    private lateinit var flexBox: FlexboxLayout

    private lateinit var userType: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_profile, container, false)

        drawerLayout = layout.findViewById(R.id.drawerLayout)
        drawer = layout.findViewById(R.id.ivDrawer)
        navView = layout.findViewById(R.id.navView)
        addFab = layout.findViewById(R.id.fabShowLayoutProfile)
        linearLayout = layout.findViewById(R.id.llLayoutProfile)
        uploadPS = layout.findViewById(R.id.fabUploadPS)
        uploadPitch = layout.findViewById(R.id.fabUploadPitch)
        firebaseAuth = FirebaseAuth.getInstance()
        name = layout.findViewById(R.id.tvNameProfile)
        type = layout.findViewById(R.id.tvTypeProfile)
        flexBox = layout.findViewById(R.id.flexBoxProfile)

        linearLayout.tag = "close"

        uploadPS.setOnClickListener {
            val intent = Intent(activity,UploadYourPitch::class.java)
            intent.putExtra("userType",userType)
            startActivity(intent)
        }

        uploadPitch.setOnClickListener {
            startActivity(Intent(context, UploadYourPitch::class.java))
        }

        addFab.setOnClickListener {
            if (linearLayout.tag.equals("close")) {
                linearLayout.visibility = View.VISIBLE
                addFab.setImageResource(R.drawable.ic_cross)
                linearLayout.tag = "open"
            } else {
                linearLayout.visibility = View.INVISIBLE
                addFab.setImageResource(R.drawable.ic_add)
                linearLayout.tag = "close"
            }
        }

        drawerLayout.tag = "Close"

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.editProfileDrawer -> {
                    val intentEdit = Intent(context, EditProfile::class.java)
                    intentEdit.putExtra("type", userType)
                    startActivity(intentEdit)
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    this.drawerLayout.tag = "Close"
                }
                R.id.settingsDrawer -> {
                    Toast.makeText(
                        context,
                        "Aaj settings bnaega...kl baithke use kr painga...han meri jaaan",
                        Toast.LENGTH_SHORT
                    ).show()
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    this.drawerLayout.tag = "Close"
                }
                R.id.aboutDrawer -> {
                    Toast.makeText(
                        context,
                        "Aaj hmare baare mein jaanlega...kl bline mein khde hke photo khicaheinga...han meri jaaan",
                        Toast.LENGTH_SHORT
                    ).show()
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    this.drawerLayout.tag = "Close"
                }
                R.id.logout -> {
                    val alert = AlertDialog.Builder(requireContext())
                    alert.setTitle("Logout requested!!")
                        .setMessage("You sure you want to logout?")
                        .setPositiveButton("Okay") { _, _ ->
                            firebaseAuth.signOut()
                            startActivity(Intent(context, LoginOthers::class.java))
                        }
                        .setNegativeButton("No") { _, _ -> }
                        .create()
                        .show()

                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    this.drawerLayout.tag = "Close"
                }
            }
            true
        }
        drawer.setOnClickListener {
            if (this.drawerLayout.tag.equals("Open")) {
                this.drawerLayout.closeDrawer(GravityCompat.START)
                this.drawerLayout.tag = "Close"
            } else {
                this.drawerLayout.openDrawer(navView)
                this.drawerLayout.tag = "Open"
            }
        }

        getInfo()

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoItems = ArrayList<VideoItem>()
//        FirebaseDatabase.getInstance().reference.child("Video")
//            .addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    for (dataSnap in snapshot.children) {
//                        val video: VideoD? = dataSnap.getValue(VideoD::class.java)
//                        if (video?.uid == firebaseAuth.currentUser?.uid) {
//                            videoItems.add(
//                                VideoItem(
//                                    video!!.uri,
//                                    video.ideaName,
//                                    video.name,
//                                    video.type
//                                )
//                            )
//                            Log.d("check url", video.uri)
//                        }
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//
//            })

        val videoItem1 = VideoItem(
            url = "https://res.cloudinary.com/dz9lxwqgj/video/upload/v1647809475/WhatsApp_Video_2022-03-21_at_1.56.14_AM_qwnrg7.mp4",
            ideaName = "SpaceX",
            founderName = "Elon Musk",
            "student"
        )
        val videoItem2 = VideoItem(
            url = "https://res.cloudinary.com/dz9lxwqgj/video/upload/v1647809456/WhatsApp_Video_2022-03-21_at_1.56.28_AM_y0mydf.mp4",
            ideaName = "Radio",
            founderName = "Marie Curie",
            "fellow"
        )
        videoItems.add(videoItem1)
        videoItems.add(videoItem2)

        val params = FlexboxLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(24)
        for (i in videoItems.indices) {
            val videoView = VideoView(activity)
            videoView.layoutParams = params
            videoView.setVideoPath(videoItems[i].url)
            videoView.seekTo(1)
            flexBox.addView(videoView)
            videoView.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container,ProfilePitchFragment()).commit()
            }
        }
    }

    private fun getInfo() {

        FirebaseDatabase.getInstance().getReference("UsersID")
            .child(firebaseAuth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(UserType::class.java)
                    userType = user!!.Type
                    Log.d("user",userType)
                    Log.d("userID",firebaseAuth.currentUser!!.uid)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

        FirebaseDatabase.getInstance().reference.child("Users").child("Student")
            .child(firebaseAuth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    name.text = user?.Name
                    type.text = userType
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })


    }
}