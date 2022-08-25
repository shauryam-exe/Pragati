package com.code.pragati.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.code.pragati.R
import com.code.pragati.adapters.PSAdapter
import com.code.pragati.model.PS
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var search: ImageView
    private lateinit var options: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var psAdapter: PSAdapter
    private lateinit var list: ArrayList<PS>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_home, container, false)

        search = layout.findViewById(R.id.ivSearchHome)
        options = layout.findViewById(R.id.ivOptionsHome)
        recyclerView = layout.findViewById(R.id.rvPSHomeFragment)
        list = ArrayList()
        psAdapter = PSAdapter(requireContext(), list)

        recyclerView.adapter = psAdapter
        val llLayout = LinearLayoutManager(requireContext())

        //To get the latest entry on top.
        llLayout.stackFromEnd = true
        llLayout.reverseLayout = true
        recyclerView.layoutManager = llLayout

        fetchPS()

        search.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, Search())?.commit()

        }

        return layout
    }

    private fun fetchPS() {
        FirebaseDatabase.getInstance().reference.child("PS")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnap in snapshot.children){
                        val ps: PS? = dataSnap.getValue(PS::class.java)
                        list.add(ps!!)
                    }
                    psAdapter?.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }
}