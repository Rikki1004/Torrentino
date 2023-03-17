package com.rikkimikki.torrentino.domain.pojo.anime

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Posters(
    @SerializedName("small")
    @Expose
    val small: PosterCurrentSize,

    @SerializedName("medium")
    @Expose
    val medium: PosterCurrentSize,

    @SerializedName("original")
    @Expose
    val original: PosterCurrentSize
)