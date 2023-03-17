package com.rikkimikki.torrentino.presentation.ui.films

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.databinding.FragmentFilmDetailBinding
import com.rikkimikki.torrentino.domain.pojo.MovieTypes
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Film
import com.rikkimikki.torrentino.utils.NO_POSTER_URL
import com.squareup.picasso.Picasso

private const val ARG_ID = "ID"
private const val ARG_TYPE = "TYPE"
private const val ARG_CONTAINER = "CONTAINER"
private const val ARG_IS_ANIME = "IS_ANIME"


class FilmDetailFragment : Fragment() {
    private var id: Int = 0
    private var container: Int = 0
    private lateinit var type: String
    private var isAnime: Boolean = false

    private var _binding: FragmentFilmDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CategoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            if (it.containsKey(ARG_ID) && it.containsKey(ARG_TYPE) && it.containsKey(ARG_CONTAINER) && it.containsKey(ARG_IS_ANIME)){
                id = it.getInt(ARG_ID)
                container = it.getInt(ARG_CONTAINER)
                type = it.getString(ARG_TYPE)!!
                isAnime = it.getBoolean(ARG_IS_ANIME)
            }
            else
                throw Exception()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]

        startObservers()

        if (type.uppercase() == MovieTypes.FILM.name)
            viewModel.getFilm(id)
        else if(type == MovieTypes.ANIME.name)
            viewModel.getAnime(id)
        else
            viewModel.getTvSerie(id)

    }

    private fun startObservers() {
        viewModel.film.observe(viewLifecycleOwner){film ->
            configurate(film)
        }
        viewModel.tvSerie.observe(viewLifecycleOwner){tvSerie ->
            configurate(tvSerie.data.film)
        }
        viewModel.anime.observe(viewLifecycleOwner){anime ->
            val film = anime.toFilm()
            configurate(film)
            binding.playButton.setOnClickListener {
                val fragment = SearchTorrentsFragment.newInstance(
                    film.title.russian,
                    film.poster?.posterLibria ?: NO_POSTER_URL,
                    anime.torrents.toData()
                )
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun configurate(film: Film){
        with(binding) {
            val poster = if (film.poster != null){
                if (isAnime) film.poster.posterLibria else film.poster.posterKpBig
            }else {
                NO_POSTER_URL
            }
            Picasso.get()
                .load(poster)
                .placeholder(R.drawable.malevich)
                .error(R.drawable.placeholder)
                .into(imageViewBigPoster)
            textViewTitle.text = film.title.russian
            textViewOrigTitle.text = if (film.title.original == null) film.title.russian else film.title.original
            textViewRating.text = film.rating.kinopoiskReviews.count.toString() + " / " + film.rating.kinopoiskReviews.value
            textViewReleaseDate.text = film.dateRelease.toString()
            textViewOverview.text = film.overview

            if (film.mainTraller != null ) {
                Picasso.get().load(film.mainTraller.mainTrallerPreview.preview)
                    .into(imageViewTrallerPreview)
                imageViewTrallerPreview.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(
                        Uri.parse(film.mainTraller.url),
                        "video/*"
                    )
                    startActivity(intent)
                }
            } else {
                imageViewTrallerPreview.visibility = View.GONE
                imageViewPlay.visibility = View.GONE
            }

            if (!isAnime)
                binding.playButton.setOnClickListener {
                    val fragment = SearchTorrentsFragment.newInstance(film.title.russian, poster)
                    requireActivity().supportFragmentManager.beginTransaction()
                        .add(container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
        }
    }


    companion object {
        fun newInstance(id: Int, type: String,container:Int,is_amine:Boolean = false) =
            FilmDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                    putString(ARG_TYPE, type)
                    putInt(ARG_CONTAINER, container)
                    putBoolean(ARG_IS_ANIME,is_amine)
                }
            }
    }
}