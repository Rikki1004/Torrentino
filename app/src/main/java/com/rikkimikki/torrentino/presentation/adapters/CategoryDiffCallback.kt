package com.rikkimikki.torrentino.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.rikkimikki.torrentino.domain.pojo.category.Category

object CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.graphName == newItem.graphName
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

}