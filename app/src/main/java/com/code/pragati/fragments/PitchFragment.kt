package com.code.pragati.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.code.pragati.model.VideoItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.viewpager2.widget.ViewPager2
import com.code.pragati.R
import com.code.pragati.adapters.VideoAdapter

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

    private fun showVideo(videoItems : ArrayList<VideoItem>){


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoViewPager = view.findViewById(R.id.videoViewPager)

        val videoItems = arrayListOf<VideoItem>()



        FirebaseDatabase.getInstance().reference.child("Video")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
//                    for (dataSnaps in snapshot.children){
//                        val video = snapshot.getValue(Video::class.java)
//                        val url = video?.uri.toString()
//                        Toast.makeText(context, video?.name, Toast.LENGTH_SHORT).show()
//                        val videoItem = VideoItem(url, "nothing", "ahvckajc", "student")
//                        videoItems.add(videoItem)
//                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


            }
            )

        val videoItem4 = VideoItem(
            url = "https://firebasestorage.googleapis.com/v0/b/pragati-a904c.appspot.com/o/videos%2F1661455444724.mp4?alt=media&token=fa5b09bd-3ff3-4777-bc75-e9b48bbd84ed",
            ideaName = "Sugar Cosmetics",
            founderName = "Jenifer",
            "student")
        videoItems.add(videoItem4)
        val videoItem5 = VideoItem(
            url = "https://firebasestorage.googleapis.com/v0/b/pragati-a904c.appspot.com/o/videos%2F1661455444724.mp4?alt=media&token=fa5b09bd-3ff3-4777-bc75-e9b48bbd84ed",
            ideaName = "Sugar Cosmetics",
            founderName = "Jenifer",
            "student")
        videoItems.add(videoItem5)
        val videoItem3 = VideoItem(
            url = "https://firebasestorage.googleapis.com/v0/b/pragati-a904c.appspot.com/o/videos%2F1661455444724.mp4?alt=media&token=fa5b09bd-3ff3-4777-bc75-e9b48bbd84ed",
            ideaName = "Sugar Cosmetics",
            founderName = "Jenifer",
            "student")
        videoItems.add(videoItem3)
        val videoItem2 = VideoItem(
            url = "https://firebasestorage.googleapis.com/v0/b/pragati-a904c.appspot.com/o/videos%2F1661455444724.mp4?alt=media&token=fa5b09bd-3ff3-4777-bc75-e9b48bbd84ed",
            ideaName = "Sugar Cosmetics",
            founderName = "Jenifer",
            "student")
        videoItems.add(videoItem2)

        videoViewPager.adapter = VideoAdapter(videoItems)
    }


}