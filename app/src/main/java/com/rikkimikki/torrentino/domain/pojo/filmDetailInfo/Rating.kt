package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rating (
    @SerializedName("kinopoisk")
    @Expose
    val kinopoiskReviews: KinopoiskReviews,
    val libriaReviews:Int=0
)