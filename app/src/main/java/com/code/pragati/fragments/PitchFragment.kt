package com.code.pragati.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.code.pragati.R
import com.code.pragati.adapters.VideoAdapter
import com.code.pragati.model.VideoItem

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PitchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PitchFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pitch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoViewPager = view.findViewById(R.id.videoViewPager)

        val videoItems = arrayListOf<VideoItem>()

        val videoItem4 = VideoItem(
        url = "https://res.cloudinary.com/dz9lxwqgj/video/upload/v1647833366/1_Minute_Sales_Pitch_mom4l1.mp4",
        ideaName = "Sugar Cosmetics",
        founderName = "Jenifer",
        "student")
        videoItems.add(videoItem4)

        val videoItem5 = VideoItem(
       url = "https://res.cloudinary.com/dz9lxwqgj/video/upload/v1647833812/Selling_strategically_one-minute_sales_pitch_pqueew.mp4",
       ideaName = "SafeZ",
       founderName = "Lara Lopez",
            "student")
        videoItems.add(videoItem5)

        val videoItem6 = VideoItem(
        url = "https://res.cloudinary.com/dz9lxwqgj/video/upload/v1647834014/1_minute_sales_pitch_1_cqytva.mp4",
        ideaName = "Turkey's Fried",
        founderName = "Mark Wood",
            "student")
        videoItems.add(videoItem6)

        videoViewPager.adapter = VideoAdapter(videoItems)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PitchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PitchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}