package com.rikkimikki.torrentino.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.databinding.ItemFilmBinding
import com.rikkimikki.torrentino.domain.pojo.film.Film
import com.rikkimikki.torrentino.domain.pojo.film.PreFilm
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FilmsAdapter: ListAdapter<PreFilm, FilmsAdapter.FilmViewHolder>(FilmsDiffCallback){

    private var onReachEndListener: OnReachEndListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film: Film = currentList[position].film
        holder.textViewTitle.text = film.title.russian
        holder.textViewGenre.text = film.janre[0].genre
        holder.textViewRating.text = film.rating.kpRating.rating.toString()
        Picasso.get().load("https:"+film.poster.url+"/136x204").into(holder.imageViewPoster)
        if (position > currentList.size - 10 && onReachEndListener != null) {
            onReachEndListener?.onReachEnd()
        }
        holder.itemView.setOnClickListener {
            onFilmClickListener?.onFilmClick(film.id.toInt(),film.type)
        }
    }

    inner class FilmViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var viewBinding = ItemFilmBinding.bind(itemView)
        val textViewTitle= viewBinding.textViewFilmTitle
        val textViewGenre= viewBinding.textViewFilmGenre
        val textViewRating= viewBinding.textViewFilmRating
        val imageViewPoster= viewBinding.imageViewFilmPoster
    }

    var onFilmClickListener: OnFilmClickListener? = null

    interface OnFilmClickListener {
        fun onFilmClick(id: Int, type: String)
    }
    interface OnReachEndListener {
        fun onReachEnd()
    }
}
