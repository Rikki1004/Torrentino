package com.rikkimikki.torrentino.domain.pojo.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rating (
    @SerializedName("kinopoisk")
    @Expose
    val kinopoisk: Kinopoisk,

    @SerializedName("__typename")
    @Expose
    val typename: String
)