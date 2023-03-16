package com.rikkimikki.torrentino.domain.pojo.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Poster
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Title

data class Movie__1 (
    @SerializedName("id")
    @Expose
    val id :Int,

    @SerializedName("title")
    @Expose
    val title: Title,

    @SerializedName("rating")
    @Expose
    val rating: Rating,

    @SerializedName("poster")
    @Expose
    val poster: Poster?,

    @SerializedName("viewOption")
    @Expose
    val viewOption: Any,

    @SerializedName("__typename")
    @Expose
    val typename: String,

    @SerializedName("type")
    @Expose
    val type: String,

    @SerializedName("productionYear")
    @Expose
    val productionYear :Int = 0,

    @SerializedName("releaseYears")
    @Expose
    val releaseYears: List<ReleaseYear>? = null

){
    val releaseDate: String
        get() = if (productionYear != 0) productionYear.toString() else if (releaseYears != null) {
            "" + releaseYears[0].start + if (releaseYears[0].end != 0) " - " + releaseYears[0].end else ""
        } else ""
}