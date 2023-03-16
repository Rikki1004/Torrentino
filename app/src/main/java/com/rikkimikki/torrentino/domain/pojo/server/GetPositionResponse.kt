package com.rikkimikki.torrentino.domain.pojo.server

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetPositionResponse (
    @SerializedName("duration")
    @Expose
    val duration: String,

    @SerializedName("position")
    @Expose
    val position: String,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("time")
    @Expose
    val time: String,

    @SerializedName("title")
    @Expose
    val title: String
)