package com.rikkimikki.torrentino.domain.pojo.film

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Film (
    @SerializedName("__typename")
    @Expose
    var type: String,

    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("url")
    @Expose
    var url: String,

    @SerializedName("title")
    @Expose
    var title: Title,

    @SerializedName("genres")
    @Expose
    var janre: List<Genre>,

    @SerializedName("poster")
    @Expose
    var poster: Poster,

    @SerializedName("rating")
    @Expose
    var rating: Rating
)