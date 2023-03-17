package com.rikkimikki.torrentino.utils

import com.rikkimikki.torrentino.domain.pojo.torrent.Data
import com.rikkimikki.torrentino.domain.pojo.torrent.TorrentResponse
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun parseSearch(html:String) : TorrentResponse{
    val response = mutableListOf<Data>()
    val newHtml = "<table>$html</table>"
    val document: Document = Jsoup.parse(newHtml)
    for (i in document.select("tr.gai, tr.tum")){
        val children = i.select("td")
        val childrenR = children[1].select("a")
        val data = Data(
            childrenR[0].attr("href"),
            childrenR[2].text(),
            childrenR[1].attr("href"),
            children[children.size-1].getElementsByTag("span").text(),
            children[children.size-2].text()
        )
        response.add(data)
    }
    return TorrentResponse(response)
}