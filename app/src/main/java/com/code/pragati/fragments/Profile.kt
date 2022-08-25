package com.code.pragati.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.code.pragati.EditProfile
import com.code.pragati.R
import com.code.pragati.model.User
import com.code.pragati.ui.login.LoginOthers
import com.code.pragati.ui.upload.UploadPSFinal
import com.code.pragati.ui.upload.UploadYourPitch
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
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

    private lateinit var tabLayout: TabLayout
    private lateinit var scrollBar: HorizontalScrollView
    private lateinit var addFab: FloatingActionButton
    private lateinit var uploadPS: FloatingActionButton
    private lateinit var uploadPitch: FloatingActionButton
    private lateinit var linearLayout: LinearLayout
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var name : TextView
    private lateinit var type : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_profile, container, false)

        drawerLayout = layout.findViewById(R.id.drawerLayout)
        drawer = layout.findViewById(R.id.ivDrawer)
        navView = layout.findViewById(R.id.navView)
        scrollBar = layout.findViewById(R.id.scrollTabs)
        addFab = layout.findViewById(R.id.fabShowLayoutProfile)
        linearLayout = layout.findViewById(R.id.llLayoutProfile)
        uploadPS = layout.findViewById(R.id.fabUploadPS)
        uploadPitch = layout.findViewById(R.id.fabUploadPitch)
        firebaseAuth = FirebaseAuth.getInstance()
        name = layout.findViewById(R.id.tvNameProfile)
        type = layout.findViewById(R.id.tvTypeProfile)

        linearLayout.tag = "close"

        uploadPS.setOnClickListener {
            startActivity(Intent(context, UploadPSFinal::class.java))
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
                    startActivity(Intent(context, EditProfile::class.java))
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    this.drawerLayout.tag = "Close"
                }
                R.id.settingsDrawer -> {
                    startActivity(Intent(context, UploadYourPitch::class.java))
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
                   firebaseAuth.signOut()
                    startActivity(Intent(context, LoginOthers::class.java))
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
        tabLayout = layout.findViewById(R.id.tabs)

        //To set different width for a tab.
        setTabWidth(0, 1.0f, tabLayout)
        setTabWidth(1, 1.0f, tabLayout)
        setTabWidth(2, 1.0f, tabLayout)
        setTabWidth(3, 1.0f, tabLayout)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> Toast.makeText(context, "This is timeline", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(context, "This is photo", Toast.LENGTH_SHORT).show()
                    2 -> Toast.makeText(context, "This is friends", Toast.LENGTH_SHORT).show()
                    3 -> Toast.makeText(context, "This is groups", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        getInfo()

        return layout
    }

    private fun getInfo() {

        FirebaseDatabase.getInstance().reference.child("Users").child("Student").child(firebaseAuth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                   name.text = user?.Name
                   type.text = "Student"
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
}