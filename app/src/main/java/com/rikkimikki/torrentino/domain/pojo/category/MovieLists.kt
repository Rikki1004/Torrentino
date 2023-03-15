package com.rikkimikki.torrentino.domain.pojo.category

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieLists (
    @SerializedName("items")
    @Expose
    val items: List<Category>
)