package com.code.pragati.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    var uri: Uri? = null,
    var likes: String? = "",
    var uid: String = "",
    var ideaName: String = "",
    var name : String = "",
    var type: String = ""
) : Parcelable