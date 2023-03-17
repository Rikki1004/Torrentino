package com.rikkimikki.torrentino.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.databinding.ItemFilmBinding
import com.rikkimikki.torrentino.databinding.ItemSearchBinding
import com.rikkimikki.torrentino.domain.pojo.MovieTypes
import com.rikkimikki.torrentino.domain.pojo.film.Film
import com.rikkimikki.torrentino.domain.pojo.search.Movie__1
import com.squareup.picasso.Picasso

class SearchAdapter: ListAdapter<Movie__1, SearchAdapter.SearchViewHolder>(SearchDiffCallback){

    var onFilmClickListener: OnFilmClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val film = currentList[position]
        holder.textViewTitle.text = film.title.russian
        holder.textViewEnTitle.text = film.title.original ?: film.title.russian
        holder.textViewRating.text = film.rating.kinopoisk.value.toString()
        holder.textViewReleaseDate.text = film.releaseDate
        if (film.poster!=null){
            val poster = if (film.type == MovieTypes.ANIME.name)
                film.poster.posterLibria
            else
                film.poster.posterKP
            Picasso.get().load(poster).into(holder.imageViewPoster)
        }else{
            Picasso.get().load(R.drawable.placeholder).into(holder.imageViewPoster)
        }


        holder.itemView.setOnClickListener {
            onFilmClickListener?.onFilmClick(film.id,film.typename)
        }
    }

    inner class SearchViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var viewBinding = ItemSearchBinding.bind(itemView)
        val textViewTitle= viewBinding.textViewSearchTitle
        val textViewEnTitle= viewBinding.textViewSearchEnTitle
        val textViewRating= viewBinding.textViewSearchRating
        val textViewReleaseDate= viewBinding.textViewSearchReleaseDate
        val imageViewPoster= viewBinding.imageViewSearchImage
    }



    interface OnFilmClickListener {
        fun onFilmClick(id: Int, type: String)
    }
}