package com.rikkimikki.torrentino.domain.pojo.film

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Poster (
    @SerializedName("avatarsUrl")
    @Expose
    val url: String
){
    val posterKpSmall:String
        get() = "https:$url/136x204"
}