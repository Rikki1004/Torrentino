package com.rikkimikki.torrentino.domain.pojo.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Suggest (
    @SerializedName("top")
    @Expose
    val top: Top,

    @SerializedName("__typename")
    @Expose
    val typename: String
)