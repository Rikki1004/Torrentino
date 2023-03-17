package com.rikkimikki.torrentino.domain.pojo.anime

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Names (

    @SerializedName("ru")
    @Expose
    val ru : String,

    @SerializedName("en")
    @Expose
    val en : String

)