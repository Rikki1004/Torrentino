package com.rikkimikki.torrentino.utils

import com.rikkimikki.torrentino.domain.pojo.server.MiniTorrent
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

fun dataToMiniTorrents(data:String):List<MiniTorrent>{
    val result = arrayListOf<MiniTorrent>()
    try {
        val torrents: JSONArray = JSONObject(data).getJSONObject("TorrServer").getJSONArray("Files")
        for (i in 0 until torrents.length()) {
            result.add(
                MiniTorrent(
                    torrents.getJSONObject(i)["path"].toString(),
                    torrents.getJSONObject(i)["id"].toString().toInt()
            ))
        }
    } catch (e: JSONException) {
        result.add(MiniTorrent("film",1))
    }
    return result
}