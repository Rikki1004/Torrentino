package com.rikkimikki.torrentino.domain.pojo.film

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movies (
    @SerializedName("items")
    @Expose
    var preFilms: List<PreFilm>
)