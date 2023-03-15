package com.rikkimikki.torrentino.domain.pojo.category

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data (
    @SerializedName("movieListCategory")
    @Expose
    val movieListCategory: MovieListCategory
)