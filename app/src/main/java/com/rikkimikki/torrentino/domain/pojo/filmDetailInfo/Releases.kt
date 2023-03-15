package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Releases (
    @SerializedName("releases")
    @Expose
    var releases: List<Release>
)