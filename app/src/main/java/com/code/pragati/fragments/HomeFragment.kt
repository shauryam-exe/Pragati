package com.code.pragati.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.code.pragati.R
import com.code.pragati.adapters.PSAdapter
import com.code.pragati.adapters.ResourcesAdapter
import com.code.pragati.model.PS
import com.code.pragati.model.Resources
import com.google.android.material.tabs.TabLayout
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
    private lateinit var recyclerViewResources: RecyclerView
    private lateinit var recyclerViewReport: RecyclerView
    private lateinit var psAdapter: PSAdapter
    private lateinit var list: ArrayList<PS>
    private lateinit var resourcesAdapter: ResourcesAdapter
    private lateinit var reportsAdapter: ResourcesAdapter
    private lateinit var listResources: ArrayList<Resources>
    private lateinit var listReports: ArrayList<Resources>

    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_home, container, false)

        search = layout.findViewById(R.id.ivSearchHome)
        options = layout.findViewById(R.id.ivOptionsHome)
        recyclerView = layout.findViewById(R.id.rvPSHomeFragment)
        recyclerViewReport = layout.findViewById(R.id.rvReportHomeFragment)
        recyclerViewResources = layout.findViewById(R.id.rvResourcesHomeFragment)
        list = ArrayList()
        listResources = ArrayList()
        listReports = ArrayList()
        psAdapter = PSAdapter(requireContext(), list)
        resourcesAdapter = ResourcesAdapter(requireContext(), listResources)
        reportsAdapter = ResourcesAdapter(requireContext(), listReports)

        tabLayout = layout.findViewById(R.id.tabs)

        recyclerView.adapter = psAdapter
        val llLayout = LinearLayoutManager(requireContext())

        //To get the latest entry on top.
        llLayout.stackFromEnd = true
        llLayout.reverseLayout = true
        recyclerView.layoutManager = llLayout


        recyclerViewResources.adapter = resourcesAdapter
        val llLayoutR = LinearLayoutManager(requireContext())

//        //To get the latest entry on top.
//        llLayoutR.stackFromEnd = true
//        llLayoutR.reverseLayout = true
        recyclerViewResources.layoutManager = llLayoutR

        recyclerViewReport.adapter = reportsAdapter
        val llLayoutRe = LinearLayoutManager(requireContext())

//        //To get the latest entry on top.
//        llLayoutRe.stackFromEnd = true
//        llLayoutRe.reverseLayout = true
        recyclerViewReport.layoutManager = llLayoutRe

        fetchPS()
        fetchResources()
        fetchReports()

        search.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, Search())?.commit()

        }
        //To set different width for a tab.
        setTabWidth(0, 0.4f, tabLayout)
        setTabWidth(1, 1.0f, tabLayout)
        setTabWidth(2, 1.0f, tabLayout)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        recyclerView.visibility = View.VISIBLE
                        recyclerViewResources.visibility = View.GONE
                        recyclerViewReport.visibility = View.GONE
                    }
                    1 -> {
                        recyclerView.visibility = View.GONE
                        recyclerViewResources.visibility = View.VISIBLE
                        recyclerViewReport.visibility = View.GONE
                    }
                    2 -> {
                        recyclerView.visibility = View.GONE
                        recyclerViewResources.visibility = View.GONE
                        recyclerViewReport.visibility = View.VISIBLE
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


        return layout
    }

    private fun fetchReports() {

        FirebaseDatabase.getInstance().reference.child("Reports")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnap in snapshot.children){
                        val resource: Resources? = dataSnap.getValue(Resources::class.java)
                        if(!listReports.contains(resource)){
                            listReports.add(resource!!)
                        }
                        Log.d("check", resource!!.text)
                    }
                    reportsAdapter?.notifyDataSetChanged()
                }


                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun fetchResources() {
        FirebaseDatabase.getInstance().reference.child("Resources")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnap in snapshot.children){
                        val resource: Resources? = dataSnap.getValue(Resources::class.java)
                        if(!listResources.contains(resource)){
                            listResources.add(resource!!)
                        }
                        Log.d("check", resource!!.text)
                    }
                    resourcesAdapter?.notifyDataSetChanged()
                }


                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    //Function to set different width for a tab.
    private fun setTabWidth(tabPosition: Int, weight: Float, tabLayout: TabLayout) {
        val layout: LinearLayout =
            (tabLayout.getChildAt(0) as LinearLayout).getChildAt(tabPosition) as LinearLayout
        val layoutParams: LinearLayout.LayoutParams =
            layout.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = weight
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        layout.layoutParams = layoutParams

        val tabLayoutParams: ViewGroup.LayoutParams? = tabLayout.layoutParams
        tabLayoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT
        tabLayout.layoutParams = tabLayoutParams
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}