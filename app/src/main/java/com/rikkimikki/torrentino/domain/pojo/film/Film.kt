package com.rikkimikki.torrentino.domain.pojo.film

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Film (
    @SerializedName("__typename")
    @Expose
    val type: String,

    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("url")
    @Expose
    val url: String,

    @SerializedName("title")
    @Expose
    val title: Title,

    @SerializedName("genres")
    @Expose
    val janre: List<Genre>,

    @SerializedName("poster")
    @Expose
    val poster: Poster?,

    @SerializedName("rating")
    @Expose
    val rating: Rating
)