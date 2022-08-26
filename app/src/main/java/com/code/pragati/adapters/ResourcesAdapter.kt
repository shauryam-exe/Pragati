package com.code.pragati.adapters

import android.content.ActivityNotFoundException
import com.code.pragati.model.Resources
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.code.pragati.R
import com.code.pragati.model.PS
import com.google.firebase.auth.FirebaseUser
import java.util.ArrayList

class ResourcesAdapter(private var context: Context, private var data : ArrayList<Resources>): RecyclerView.Adapter<ResourcesAdapter.ViewHolder>() {

    var firebaseUser : FirebaseUser? = null

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val text : TextView = itemView.findViewById(R.id.tvResources)
        val card : CardView = itemView.findViewById(R.id.cardResources)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_resources_home, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resources = data[position]
        holder.text.text = resources.text
        holder.card.setOnClickListener {
            val intent =
               Intent(Intent.ACTION_VIEW, Uri.parse(resources.text))
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}