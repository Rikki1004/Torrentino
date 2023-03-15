package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainTrailer (
    @SerializedName("streamUrl")
    @Expose
    var url: String,

    @SerializedName("preview")
    @Expose
    var mainTrallerPreview: MainTrailerPreview
)