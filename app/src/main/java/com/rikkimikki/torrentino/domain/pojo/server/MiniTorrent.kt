package com.rikkimikki.torrentino.domain.pojo.server

class MiniTorrent(
    val name:String,
    val id:Int
){
    override fun toString(): String {
        return name
    }
}