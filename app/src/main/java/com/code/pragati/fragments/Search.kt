package com.code.pragati.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.code.pragati.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Search : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var search : EditText
    private lateinit var searchIcon : ImageView
    private lateinit var crossIcon : ImageView
    private lateinit var back : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_search, container, false)

        search = layout.findViewById(R.id.etSearch)
        searchIcon = layout.findViewById(R.id.ivSearchFragment)
        crossIcon = layout.findViewById(R.id.ivSearchFragmentCross)
        back = layout.findViewById(R.id.ivBackSearch)

        back.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, HomeFragment())?.commit()
        }

        //To change the image
        search.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                searchIcon.visibility = View.GONE
                crossIcon.visibility = View.VISIBLE
            } else {
                searchIcon.visibility = View.VISIBLE
                crossIcon.visibility = View.GONE
            }
        }

        return layout
    }
}