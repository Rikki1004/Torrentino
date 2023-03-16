package com.rikkimikki.torrentino.domain.pojo.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Top (
    @SerializedName("topResult")
    @Expose
    val topResult: TopResult,

    @SerializedName("movies")
    @Expose
    val movies: List<Movie>
)