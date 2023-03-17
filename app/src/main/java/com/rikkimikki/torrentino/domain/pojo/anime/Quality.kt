package com.rikkimikki.torrentino.domain.pojo.anime

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Quality (
    @SerializedName("string")
    @Expose
    val string:String,

    @SerializedName("type")
    @Expose
    val type:String,

    @SerializedName("resolution")
    @Expose
    val resolution:String,

    @SerializedName("encoder")
    @Expose
    val encoder:String
)