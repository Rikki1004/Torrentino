package com.rikkimikki.torrentino.domain.pojo.anime

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AnimeResponseMain(
    @SerializedName("list")
    @Expose
    val list:List<AnimeResponse>,
)