package com.rikkimikki.torrentino.domain.pojo.film

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Title (
    @SerializedName("russian")
    @Expose
    var russian: String
)