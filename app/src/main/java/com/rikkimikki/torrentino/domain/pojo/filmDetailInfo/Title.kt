package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Title (
    @SerializedName("russian")
    @Expose
    val russian: String,

    @SerializedName("original")
    @Expose
    val original: String?
)