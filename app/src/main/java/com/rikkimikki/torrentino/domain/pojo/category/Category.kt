package com.rikkimikki.torrentino.domain.pojo.category

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("cover")
    @Expose
    val cover: Cover,

    @SerializedName("currentFilters")
    @Expose
    val currentFilters: CurrentFilters
){
    val graphName: String
        get() = currentFilters.singleSelectFilterValues[0].value
    val poster: String
        get() = cover.avatarsUrl
}