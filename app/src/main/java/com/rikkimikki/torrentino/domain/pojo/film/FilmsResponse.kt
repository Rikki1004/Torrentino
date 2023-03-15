package com.rikkimikki.torrentino.domain.pojo.film

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilmsResponse (
    @SerializedName("data")
    @Expose
    var data: Data
)