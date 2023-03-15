package com.rikkimikki.torrentino.domain.pojo.category

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieListCategory (
    @SerializedName("movieLists")
    @Expose
    val movieLists: MovieLists
)