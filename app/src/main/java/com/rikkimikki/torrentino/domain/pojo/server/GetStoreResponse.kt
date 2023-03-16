package com.rikkimikki.torrentino.domain.pojo.server

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetStoreResponse (
    @SerializedName("data")
    @Expose
    val data: String? = null,

    @SerializedName("hash")
    @Expose
    val hash: String,

    @SerializedName("poster")
    @Expose
    val poster: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("stat_string")
    @Expose
    val stat_string: String,

    @SerializedName("torrent_size")
    @Expose
    val torrent_size: Double = 0.0,

    @SerializedName("pending_peers")
    @Expose
    val pending_peers: Int = 0,

    @SerializedName("active_peers")
    @Expose
    val active_peers: Int = 0,

    @SerializedName("half_open_peers")
    @Expose
    val half_open_peers: Int = 0,

    @SerializedName("upload_speed")
    @Expose
    val upload_speed: Float = 0f
){
    val uploadSpeed: String
    get() {
        return if (upload_speed != 0f) String.format(
            "%.2f",
            upload_speed / 1024
        ) + " Mb/s" else "---"
    }

    val allPeers: String
        get() = if (active_peers != 0) "$active_peers . $half_open_peers / $pending_peers" else "---"
}