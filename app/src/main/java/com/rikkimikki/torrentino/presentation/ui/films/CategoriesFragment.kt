package com.rikkimikki.torrentino.presentation.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.databinding.FragmentCategoriesBinding
import com.rikkimikki.torrentino.presentation.MainActivity
import com.rikkimikki.torrentino.presentation.adapters.CategoryAdapter

class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CategoriesViewModel

    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]


        adapter = CategoryAdapter(requireContext())
        adapter.onFilmClickListener = object : CategoryAdapter.OnFilmClickListener{
            override fun onFilmClick(id: Int, type: String) {

                val fragment =  FilmDetailFragment.newInstance(id,type,R.id.filmsFragmentContainer)
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.filmsFragmentContainer,fragment)
                    .addToBackStack(null)
                    .commit()

            }
        }
        adapter.onAllFilmButtonClickListener = object : CategoryAdapter.OnAllFilmButtonClickListener{
            override fun onButtonClick(genre: String) {

                val fragment = SelectedCategoryFragment.newInstance(genre)
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.filmsFragmentContainer,fragment)
                    .addToBackStack(null)
                    .commit()

            }
        }

        binding.RecycleViewFilms.layoutManager = LinearLayoutManager(requireActivity()).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        binding.RecycleViewFilms.adapter = adapter
        binding.RecycleViewFilms.setRecycledViewPool(RecyclerView.RecycledViewPool().apply { setMaxRecycledViews(CategoryAdapter.VIEW_TYPE,35) })

        viewModel.getCategories().observe(this){
            adapter.submitList(it.toMutableList())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}