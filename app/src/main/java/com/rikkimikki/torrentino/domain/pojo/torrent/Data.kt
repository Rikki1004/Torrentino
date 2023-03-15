package com.rikkimikki.torrentino.domain.pojo.torrent

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("link")
    @Expose
    val link: String,

    @SerializedName("film")
    @Expose
    val film: String,

    @SerializedName("magnetLink")
    @Expose
    val magnetLink: String,

    @SerializedName("peers")
    @Expose
    val peers: String,

    @SerializedName("size")
    @Expose
    val size: String

){
    override fun toString(): String {
        return "$film - $size ($peers)"
    }
}