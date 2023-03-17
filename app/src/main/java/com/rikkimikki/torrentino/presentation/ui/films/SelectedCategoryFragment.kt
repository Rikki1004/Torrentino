package com.rikkimikki.torrentino.presentation.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.databinding.FragmentSelectedCategoryBinding
import com.rikkimikki.torrentino.presentation.adapters.FilmsAdapter

class SelectedCategoryFragment : Fragment() {
    private var _binding: FragmentSelectedCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CategoriesViewModel

    private lateinit var adapter: FilmsAdapter

    private lateinit var genre: String
    private lateinit var title: String
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            if (it.containsKey(ARG_GENRE)){
                genre = it.getString(ARG_GENRE)!!
                title = it.getString(ARG_TITLE)!!
            }
            else
                throw Exception()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectedCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]

        binding.toolbarCategory.title = title

        val recycleView = binding.RecycleViewSelectedCategory
        adapter = FilmsAdapter()

        recycleView.layoutManager = GridLayoutManager(context, 2)
        recycleView.adapter = adapter

        viewModel.films.observe(viewLifecycleOwner){
            adapter.addFilms(it)
        }
        adapter.onReachEndListener = object :FilmsAdapter.OnReachEndListener{
            override fun onReachEnd() {
                offset+=FILMS_COUNT
                viewModel.getFilmsWithOffset(genre,offset)
            }
        }
        adapter.onFilmClickListener = object :FilmsAdapter.OnFilmClickListener{
            override fun onFilmClick(id: Int, type: String) {
                val fragment =  FilmDetailFragment.newInstance(id,type,R.id.filmsFragmentContainer)
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.filmsFragmentContainer,fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        viewModel.getFilmsWithOffset(genre,0)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_GENRE = "genre"
        private const val ARG_TITLE = "title"
        private const val FILMS_COUNT = 50
        fun newInstance(genre: String,title:String) =
            SelectedCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_GENRE, genre)
                    putString(ARG_TITLE, title)
                }
            }
    }
}