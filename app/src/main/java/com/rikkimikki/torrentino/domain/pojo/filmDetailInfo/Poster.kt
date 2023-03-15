package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Poster (
    @SerializedName("avatarsUrl")
    @Expose
    var url: String
)