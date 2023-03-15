package com.rikkimikki.torrentino.domain.pojo.category

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Cover (
    @SerializedName("avatarsUrl")
    @Expose
    val avatarsUrl: String
)