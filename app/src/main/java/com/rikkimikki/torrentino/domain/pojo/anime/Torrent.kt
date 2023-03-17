package com.rikkimikki.torrentino.domain.pojo.anime

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Torrent (
    @SerializedName("quality")
    @Expose
    val quality:Quality,

    @SerializedName("seeders")
    @Expose
    val seeders:Int,

    @SerializedName("total_size")
    @Expose
    val total_size:Long,

    @SerializedName("size_string")
    @Expose
    val size_string:String,

    @SerializedName("url")
    @Expose
    val url:String,

    @SerializedName("magnet")
    @Expose
    val magnet:String,

    @SerializedName("hash")
    @Expose
    val hash:String
)