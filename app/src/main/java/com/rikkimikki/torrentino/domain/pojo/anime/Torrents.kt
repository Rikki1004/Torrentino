package com.rikkimikki.torrentino.domain.pojo.anime

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rikkimikki.torrentino.domain.pojo.torrent.Data

data class Torrents (
    @SerializedName("list")
    @Expose
    val list: List<Torrent>
){
    fun toData():ArrayList<Data>{
        val listData = arrayListOf<Data>()
        for (i in list){
            listData.add(Data(
                i.magnet,
                i.quality.string,
                i.magnet,
                i.seeders.toString(),
                i.size_string
            ))
        }
        return listData
    }
}