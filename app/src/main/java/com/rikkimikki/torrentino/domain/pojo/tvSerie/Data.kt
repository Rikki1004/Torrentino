package com.rikkimikki.torrentino.domain.pojo.tvSerie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Film

data class Data (
    @SerializedName("tvSeries")
    @Expose
    val film: Film
)