package com.code.pragati.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.code.pragati.R
import com.code.pragati.model.Video
import com.code.pragati.model.VideoInfo
import com.code.pragati.model.VideoItem

class VideoAdapter(val videoItems: ArrayList<VideoItem>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pitch_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.setVideoData(videoItems[position], position)
    }

    override fun getItemCount(): Int {
        return videoItems.size
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoView = itemView.findViewById<VideoView>(R.id.pitchVideo)
        val ideaName = itemView.findViewById<TextView>(R.id.ideaName)
        val founderName = itemView.findViewById<TextView>(R.id.founderName)
        val userIdentifyImage = itemView.findViewById<ImageView>(R.id.userIdentifyImage)
        val likeButton = itemView.findViewById<ImageView>(R.id.likeButton)
        val commentButton = itemView.findViewById<ImageView>(R.id.commentButton)
        val infoButton = itemView.findViewById<ImageView>(R.id.infoButton)
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBarPitchVideo)

        fun setVideoData(video: VideoItem, position: Int) {
            ideaName.text = video.ideaName
            founderName.text = video.founderName
            when (video.founderType) {
                "student" -> {}
                "founder" -> {}
                "fellow" -> {}
            }

            videoView.setVideoPath(video.url)

            videoView.setOnPreparedListener() {
                progressBar.visibility = View.GONE
                it.start()
                val videoRatio: Float = it.videoWidth / it.videoHeight.toFloat()
                val screenRatio: Float = videoView.width / videoView.height.toFloat()

                val scale: Float = screenRatio / videoRatio
                if (scale >= 1) {
                    videoView.scaleX = scale
                } else {
                    videoView.scaleY = 1 / scale
                }

            }
            videoView.setOnCompletionListener {
                it.start()
            }

        }
    }
}