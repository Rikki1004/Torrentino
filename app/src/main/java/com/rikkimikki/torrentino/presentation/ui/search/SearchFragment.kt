package com.rikkimikki.torrentino.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.databinding.FragmentSearchBinding
import com.rikkimikki.torrentino.presentation.adapters.SearchAdapter
import com.rikkimikki.torrentino.presentation.ui.films.FilmDetailFragment


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter
    private var isAnime = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        initSearchView()
        initAdapter()
        binding.switchAnimeSearch.setOnCheckedChangeListener{ _, isChecked ->
            isAnime = isChecked
        }
    }

    private fun initAdapter() {
        val recycleView = binding.recycleViewSearch
        adapter = SearchAdapter()

        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter

        viewModel.films.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        adapter.onFilmClickListener = object : SearchAdapter.OnFilmClickListener{
            override fun onFilmClick(id: Int, type: String) {
                val fragment = FilmDetailFragment.newInstance(id,type,R.id.searchFragmentContainer,isAnime)
                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.searchFragmentContainer,fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun initSearchView() {
        val sv = binding.searchViewSearch
        sv.setIconifiedByDefault(true)
        sv.isFocusable = true
        sv.isIconified = false
        sv.clearFocus()
        sv.requestFocusFromTouch()

        binding.searchViewSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null && p0.length >= 3)
                    if (isAnime)
                        viewModel.searchAnime(p0)
                    else
                        viewModel.search(p0)
                else
                    adapter.submitList(null)
                return false
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}