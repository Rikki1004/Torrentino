package com.rikkimikki.torrentino.domain.pojo.server

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddTorrentResponse (
    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("hash")
    @Expose
    val hash: String,

    @SerializedName("data")
    @Expose
    val data: String,

    @SerializedName("file_stats")
    @Expose
    val file_stats: List<FileStats>
)