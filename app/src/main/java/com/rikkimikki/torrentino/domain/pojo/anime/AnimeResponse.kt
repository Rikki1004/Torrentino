package com.rikkimikki.torrentino.domain.pojo.anime

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rikkimikki.torrentino.domain.pojo.MovieTypes
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.*
import com.rikkimikki.torrentino.domain.pojo.search.Kinopoisk
import com.rikkimikki.torrentino.domain.pojo.search.Movie__1
import com.rikkimikki.torrentino.domain.pojo.search.Rating

data class AnimeResponse (
    @SerializedName("id")
    @Expose
    val id:Int,

    @SerializedName("names")
    @Expose
    val names:Names,

    @SerializedName("posters")
    @Expose
    val posters:Posters,

    @SerializedName("season")
    @Expose
    val season:Season,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("in_favorites")
    @Expose
    val in_favorites: Int,

    @SerializedName("torrents")
    @Expose
    val torrents:Torrents
){
    fun toMovie(): Movie__1 {
        return Movie__1(
            id,
            Title(names.ru, names.en),
            Rating(Kinopoisk(0f), ""),
            Poster(posters.small.url),
            Any(),
            MovieTypes.ANIME.name,
            MovieTypes.ANIME.name,
            season.year.toInt()
        )
    }
    fun toFilm(): Film {
        return Film(
            Title(names.ru, names.en),
            null,
            season.year.toInt(),
            Rating(KinopoiskReviews(0,0f),in_favorites),
            description,
            //Poster(posters.small.url),
            Gallery(Posters(Poster(posters.small.url))) ,
            Ott(PromoTrailers(listOf()))
        )
    }
}