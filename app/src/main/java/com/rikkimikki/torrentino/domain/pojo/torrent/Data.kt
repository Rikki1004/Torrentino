package com.rikkimikki.torrentino.domain.pojo.torrent

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data (
    @SerializedName("link")
    @Expose
    val link: String,

    @SerializedName("film")
    @Expose
    var film: String,

    @SerializedName("magnetLink")
    @Expose
    val magnetLink: String,

    @SerializedName("peers")
    @Expose
    val peers: String,

    @SerializedName("size")
    @Expose
    val size: String,

    var poster:String = ""

):Parcelable{
    override fun toString(): String {
        return "$film - $size ($peers)"
    }
}