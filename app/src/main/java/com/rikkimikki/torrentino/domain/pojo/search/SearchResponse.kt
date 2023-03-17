package com.rikkimikki.torrentino.domain.pojo.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("data")
    @Expose
    val data: Data
){
    fun getData(): List<Movie__1> {
        val oneList: MutableList<Movie__1> = ArrayList()
        if (data.suggest.top.topResult.global.typename != "Person") oneList.add(data.suggest.top.topResult.global)
        for (i in data.suggest.top.movies) oneList.add(i.movie)
        return oneList
    }
}