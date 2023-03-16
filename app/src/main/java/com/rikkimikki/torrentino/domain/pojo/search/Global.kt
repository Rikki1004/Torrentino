package com.rikkimikki.torrentino.domain.pojo.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Poster
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Title

data class Global (
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
    val poster: Poster,

    @SerializedName("productionYear")
    @Expose
    val productionYear :Int,

    @SerializedName("__typename")
    @Expose
    val typename: String
)