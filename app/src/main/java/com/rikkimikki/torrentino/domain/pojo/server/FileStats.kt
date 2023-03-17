package com.rikkimikki.torrentino.domain.pojo.server

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FileStats (
    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("length")
    @Expose
    val length: String,

    @SerializedName("path")
    @Expose
    val path: String
)