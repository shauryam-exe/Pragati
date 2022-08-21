package com.code.pragati.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.code.pragati.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Inbox : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var search : ImageView
    private lateinit var newChat : ImageView
    private lateinit var rvRecent : RecyclerView
    private lateinit var rvSaved : RecyclerView
    private lateinit var recentCard : CardView
    private lateinit var savedCard : CardView
    private lateinit var recentText : TextView
    private lateinit var savedText : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_inbox, container, false)

        search = layout.findViewById(R.id.ivSearchInbox)
        newChat = layout.findViewById(R.id.ivNewInbox)
        rvRecent = layout.findViewById(R.id.rvRecentInbox)
        rvSaved = layout.findViewById(R.id.rvSavedInbox)
        recentCard = layout.findViewById(R.id.cardRecent)
        savedCard = layout.findViewById(R.id.cardSaved)
        recentText = layout.findViewById(R.id.tvRecentAlone)
        savedText = layout.findViewById(R.id.tvSavedAlone)

        recentText.setOnClickListener {
            Toast.makeText(context, "OOoo pencho...recent aaa..kukkad murge paado", Toast.LENGTH_SHORT).show()
            recentCard.visibility = View.VISIBLE
            rvRecent.visibility = View.VISIBLE
            savedText.visibility = View.VISIBLE
            savedCard.visibility = View.GONE
            recentText.visibility = View.GONE
            rvSaved.visibility = View.GONE
        }

        savedText.setOnClickListener {
            Toast.makeText(context, "Saved Aagya Beere", Toast.LENGTH_SHORT).show()
            savedCard.visibility = View.VISIBLE
            rvSaved.visibility = View.VISIBLE
            recentText.visibility = View.VISIBLE
            recentCard.visibility = View.GONE
            savedText.visibility = View.GONE
            rvRecent.visibility = View.GONE
        }

        return layout
    }

}