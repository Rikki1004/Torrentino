package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rikkimikki.torrentino.utils.NO_POSTER_URL

data class Film (
    @SerializedName("title")
    @Expose
    val title: Title,

    @SerializedName("mainTrailer")
    @Expose
    val mainTraller: MainTrailer?,

    @SerializedName("productionYear")
    @Expose
    val dateRelease :Int,

    @SerializedName("rating")
    @Expose
    val rating: Rating,

    @SerializedName("synopsis")
    @Expose
    val overview: String,

    //@SerializedName("poster")
    //@Expose
    //val poster: Poster?,

    @SerializedName("gallery")
    @Expose
    val gallery: Gallery,

    @SerializedName("ott")
    @Expose
    val ott: Ott
)