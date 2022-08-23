package com.code.pragati.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.code.pragati.EditProfile
import com.code.pragati.R
import com.code.pragati.ui.upload.UploadYourPitch
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_profile, container, false)

        drawerLayout = layout.findViewById(R.id.drawerLayout)
        drawer = layout.findViewById(R.id.ivDrawer)
        navView = layout.findViewById(R.id.navView)

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
                    Toast.makeText(context, "Aaj settings bnaega...kl baithke use kr painga...han meri jaaan", Toast.LENGTH_SHORT).show()
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    this.drawerLayout.tag = "Close"
                }
                R.id.aboutDrawer -> {
                    Toast.makeText(context, "Aaj hmare baare mein jaanlega...kl bline mein khde hke photo khicaheinga...han meri jaaan", Toast.LENGTH_SHORT).show()
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    this.drawerLayout.tag = "Close"
                }
                R.id.logout -> {
                    Toast.makeText(context, "Aaj yeh chhod k chla jainga...kl baithke use kr roinga...han meri jaaan", Toast.LENGTH_SHORT).show()
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

        return layout
    }
}