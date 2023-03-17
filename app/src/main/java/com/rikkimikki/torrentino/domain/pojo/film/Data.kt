package com.rikkimikki.torrentino.domain.pojo.film

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data (
    @SerializedName("movieListBySlug")
    @Expose
    val movieListBySlug: MovieListBySlug
)