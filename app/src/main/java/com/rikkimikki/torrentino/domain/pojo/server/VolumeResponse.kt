package com.rikkimikki.torrentino.domain.pojo.server

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VolumeResponse (
    @SerializedName("volume")
    @Expose
    val volume :Int
)