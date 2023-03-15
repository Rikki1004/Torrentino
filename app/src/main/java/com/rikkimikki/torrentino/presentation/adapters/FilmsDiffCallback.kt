package com.rikkimikki.torrentino.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.rikkimikki.torrentino.domain.pojo.category.Category
import com.rikkimikki.torrentino.domain.pojo.film.PreFilm

object FilmsDiffCallback : DiffUtil.ItemCallback<PreFilm>() {

    override fun areItemsTheSame(oldItem: PreFilm, newItem: PreFilm): Boolean {
        return oldItem.film.id == newItem.film.id
    }

    override fun areContentsTheSame(oldItem: PreFilm, newItem: PreFilm): Boolean {
        return oldItem == newItem
    }

}