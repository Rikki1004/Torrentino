package com.rikkimikki.torrentino.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.rikkimikki.torrentino.domain.pojo.film.PreFilm
import com.rikkimikki.torrentino.domain.pojo.server.GetStoreResponse

object TorrentsDiffCallback : DiffUtil.ItemCallback<GetStoreResponse>() {
    override fun areItemsTheSame(oldItem: GetStoreResponse, newItem: GetStoreResponse): Boolean {
        return oldItem.hash == newItem.hash
    }
    override fun areContentsTheSame(oldItem: GetStoreResponse, newItem: GetStoreResponse): Boolean {
        return oldItem == newItem
    }
}