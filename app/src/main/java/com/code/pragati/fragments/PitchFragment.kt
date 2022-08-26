package com.code.pragati.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.code.pragati.R
import com.code.pragati.adapters.VideoAdapter
import com.code.pragati.model.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PitchFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var videoViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_pitch, container, false)


        return layout
    }

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoViewPager = view.findViewById(R.id.videoViewPager)
        firebaseAuth = FirebaseAuth.getInstance()

        val videoItems = arrayListOf<VideoItem>()

        FirebaseDatabase.getInstance().reference.child("Video")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnap in snapshot.children){
                        val video: VideoD? = dataSnap.getValue(VideoD::class.java)
                        videoItems.add(VideoItem(video!!.uri, video.ideaName, video.name, video.type))
                        Log.d("check url",video.uri)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })


        val videoItem2 = VideoItem(
            url = "https://res.cloudinary.com/dz9lxwqgj/video/upload/v1647809475/WhatsApp_Video_2022-03-21_at_1.56.14_AM_qwnrg7.mp4",
            ideaName = "Sugar Cosmetics",
            founderName = "Jenifer",
            "student"
        )
        videoItems.add(videoItem2)

        videoViewPager.adapter = VideoAdapter(videoItems)
    }


}