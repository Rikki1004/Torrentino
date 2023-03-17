package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rikkimikki.torrentino.utils.LIBRIA_POSTER_URL

data class Poster (
    @SerializedName("avatarsUrl")
    @Expose
    val url: String
){
    val posterKpBig:String
        get() = "https:${url}/600x900"

    val posterKP:String
        get() = "https:$url/136x204"

    val posterLibria:String
        get() = LIBRIA_POSTER_URL + url
}