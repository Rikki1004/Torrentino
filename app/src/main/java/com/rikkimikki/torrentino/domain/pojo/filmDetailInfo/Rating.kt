package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rating (
    @SerializedName("kinopoisk")
    @Expose
    var kinopoiskReviews: KinopoiskReviews
)