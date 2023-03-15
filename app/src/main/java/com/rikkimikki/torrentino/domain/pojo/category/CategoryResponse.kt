package com.rikkimikki.torrentino.domain.pojo.category

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoryResponse (
    @SerializedName("data")
    @Expose
    val data: Data
){
    fun getCategories():List<Category> {
        return data.movieListCategory.movieLists.items
    }
}
