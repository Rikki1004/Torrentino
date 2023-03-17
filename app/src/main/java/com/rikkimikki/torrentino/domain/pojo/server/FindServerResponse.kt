package com.rikkimikki.torrentino.domain.pojo.server

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FindServerResponse (
    @SerializedName("ping")
    @Expose
    val ping: String
)