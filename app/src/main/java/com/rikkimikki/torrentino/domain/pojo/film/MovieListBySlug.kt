package com.rikkimikki.torrentino.domain.pojo.film

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieListBySlug (
    @SerializedName("movies")
    @Expose
    var movie: Movies,

    @SerializedName("name")
    @Expose
    var name: String
)