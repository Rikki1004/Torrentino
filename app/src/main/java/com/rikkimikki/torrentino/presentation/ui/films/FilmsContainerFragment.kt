package com.rikkimikki.torrentino.presentation.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rikkimikki.torrentino.R

class FilmsContainerFragment : Fragment() {
    private val categoriesFragment = CategoriesFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_films_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.filmsFragmentContainer, categoriesFragment, null)
            .commit()
    }

}