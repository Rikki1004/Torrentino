package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainTrailer (
    @SerializedName("streamUrl")
    @Expose
    val url: String,

    @SerializedName("preview")
    @Expose
    val mainTrallerPreview: MainTrailerPreview
)