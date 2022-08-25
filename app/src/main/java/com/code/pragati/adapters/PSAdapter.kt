package com.code.pragati.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.code.pragati.R
import com.code.pragati.model.PS
import com.google.firebase.auth.FirebaseUser
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList

class PSAdapter(private var context: Context, private var data : ArrayList<PS>): RecyclerView.Adapter<PSAdapter.ViewHolder>() {

    var firebaseUser : FirebaseUser? = null

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.tvTitlePS)
        val description : TextView = itemView.findViewById(R.id.tvDescriptionPS)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_ps_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ps = data[position]
        holder.title.text = ps.Title
        holder.description.text = ps.Description
    }

    override fun getItemCount(): Int {
        return data.size
    }


}