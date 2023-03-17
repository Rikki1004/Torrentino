package com.rikkimikki.torrentino.domain.pojo.anime

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PosterCurrentSize (
    @SerializedName("url")
    @Expose
    val url: String
)