package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KinopoiskReviews (
    @SerializedName("count")
    @Expose
    var count :Int,

    @SerializedName("value")
    @Expose
    var value: Float
)