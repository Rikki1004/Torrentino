package com.rikkimikki.torrentino.presentation.ui.films

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rikkimikki.torrentino.R
class FilmsContainerFragment : Fragment() {
    //private val selectedCategoryFragment = SelectedCategoryFragment()
    //private val filmDetailFragment = FilmDetailFragment()
    private val categoriesFragment = CategoriesFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_films_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*requireActivity().supportFragmentManager.beginTransaction().apply {
            add(R.id.filmsFragmentContainer, categoriesFragment, null)
            add(R.id.filmsFragmentContainer, filmDetailFragment, null).hide(filmDetailFragment)
            add(R.id.filmsFragmentContainer, selectedCategoryFragment, null).hide(selectedCategoryFragment)
        }.commit()*/
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.filmsFragmentContainer, categoriesFragment, null)
            .commit()
    }

}