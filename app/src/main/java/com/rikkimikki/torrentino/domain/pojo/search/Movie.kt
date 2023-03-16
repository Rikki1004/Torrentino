package com.rikkimikki.torrentino.domain.pojo.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("movie")
    @Expose
    val movie: Movie__1,

    @SerializedName("__typename")
    @Expose
    val typename: String
)