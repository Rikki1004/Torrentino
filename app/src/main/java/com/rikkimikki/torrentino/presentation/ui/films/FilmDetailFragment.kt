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
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Film
import com.squareup.picasso.Picasso

private const val ARG_ID = "ID"
private const val ARG_TYPE = "TYPE"
private const val CONTAINER = "CONTAINER"


class FilmDetailFragment : Fragment() {
    private var id: Int = 0
    private var container: Int = 0
    private lateinit var type: String

    private var _binding: FragmentFilmDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CategoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            if (it.containsKey(ARG_ID) && it.containsKey(ARG_TYPE) && it.containsKey(CONTAINER)){
                id = it.getInt(ARG_ID)
                container = it.getInt(CONTAINER)
                type = it.getString(ARG_TYPE)!!
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

        if (type == FILM)
            viewModel.getFilm(id)
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
    }

    private fun configurate(film: Film){
        with(binding) {
            Picasso.get()
                .load("https:${film.poster.url}/600x900")
                .placeholder(R.drawable.placeholder)
                .into(imageViewBigPoster)
            textViewTitle.text = film.title.russian
            textViewOrigTitle.text = if (film.title.original == null) film.title.russian else film.title.original
            textViewRating.text = film.rating.kinopoiskReviews.count.toString() + " / " + film.rating.kinopoiskReviews.value
            textViewReleaseDate.text = film.dateRelease.toString()
            textViewOverview.text = film.overview

            if (film.mainTraller != null ) {
                Picasso.get().load("https:${film.mainTraller.mainTrallerPreview.url}/600x380")
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

            binding.playButton.setOnClickListener(View.OnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(container,SearchTorrentsFragment.newInstance(film.title.russian,"https:${film.poster.url}/600x900"))
                    .addToBackStack(null)
                    .commit()
            })
        }
    }


    companion object {
        const val FILM = "Film"
        const val TVSERIE = "TvSeries"
        fun newInstance(id: Int, type: String,container:Int) =
            FilmDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                    putString(ARG_TYPE, type)
                    putInt(CONTAINER, container)
                }
            }
    }
}