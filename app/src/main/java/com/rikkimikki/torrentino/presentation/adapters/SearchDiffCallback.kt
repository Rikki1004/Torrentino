package com.rikkimikki.torrentino.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.rikkimikki.torrentino.domain.pojo.film.PreFilm
import com.rikkimikki.torrentino.domain.pojo.search.Movie__1


object SearchDiffCallback : DiffUtil.ItemCallback<Movie__1>() {

    override fun areItemsTheSame(oldItem: Movie__1, newItem: Movie__1): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie__1, newItem: Movie__1): Boolean {
        return oldItem == newItem
    }

}