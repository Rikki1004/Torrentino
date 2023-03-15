package com.rikkimikki.torrentino.domain.pojo.film

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KPRating (
    @SerializedName("value")
    @Expose
    var rating: Float
)