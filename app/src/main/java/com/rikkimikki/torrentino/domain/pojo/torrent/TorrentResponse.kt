package com.rikkimikki.torrentino.domain.pojo.torrent

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TorrentResponse (
    @SerializedName("data")
    @Expose
    val data: List<Data>
)