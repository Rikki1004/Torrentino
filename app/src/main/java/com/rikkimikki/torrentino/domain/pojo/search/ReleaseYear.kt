package com.rikkimikki.torrentino.domain.pojo.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReleaseYear (
    @SerializedName("end")
    @Expose
    val end :Int,

    @SerializedName("start")
    @Expose
    val start :Int,

    @SerializedName("__typename")
    @Expose
    val typename: String
)