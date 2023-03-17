package com.rikkimikki.torrentino.domain.pojo.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Kinopoisk (
    @SerializedName("value")
    @Expose
    val value :Float
)