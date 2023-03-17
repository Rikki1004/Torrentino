package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KinopoiskReviews (
    @SerializedName("count")
    @Expose
    val count :Int,

    @SerializedName("value")
    @Expose
    val value: Float
)