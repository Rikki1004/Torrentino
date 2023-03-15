package com.rikkimikki.torrentino.domain.pojo.category

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentFilters (
    @SerializedName("singleSelectFilterValues")
    @Expose
    val singleSelectFilterValues: List<SingleSelectFilterValues>
)