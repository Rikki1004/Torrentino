package com.rikkimikki.torrentino.domain.pojo.tvSerie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvSerieInfoResponce (
    @SerializedName("data")
    @Expose
    val data: Data
)