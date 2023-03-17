package com.rikkimikki.torrentino.domain.pojo.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopResult (
    @SerializedName("global")
    @Expose
    val global: Movie__1,

    @SerializedName("__typename")
    @Expose
    val typename: String
)