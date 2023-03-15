package com.rikkimikki.torrentino.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.databinding.ActivityMainBinding
import com.rikkimikki.torrentino.presentation.ui.controller.ControllerFragment
import com.rikkimikki.torrentino.presentation.ui.films.CategoriesFragment
import com.rikkimikki.torrentino.presentation.ui.search.SearchFragment
import com.rikkimikki.torrentino.presentation.ui.torrents.TorrentsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val controllerFragment = ControllerFragment()
    private val torrentsFragment = TorrentsFragment()
    private val searchFragment = SearchFragment()
    private val filmsFragment = CategoriesFragment()
    private var activeFragment: Fragment = filmsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragments()
    }

    private fun setupFragments() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.mainContainer, filmsFragment, null)
            add(R.id.mainContainer, torrentsFragment, null).hide(torrentsFragment)
            add(R.id.mainContainer, searchFragment, null).hide(searchFragment)
            add(R.id.mainContainer, controllerFragment, null).hide(controllerFragment)
        }.commit()

        binding.bottomNavigationView.setOnItemSelectedListener {
            return@setOnItemSelectedListener when (it.itemId) {
                R.id.navigation_films -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(filmsFragment).commit()
                    activeFragment = filmsFragment
                    true
                }
                R.id.navigation_controller -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(controllerFragment).commit()
                    activeFragment = controllerFragment
                    true
                }
                R.id.navigation_search -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(searchFragment).commit()
                    activeFragment = searchFragment
                    true
                }
                R.id.navigation_torrents -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(torrentsFragment).commit()
                    activeFragment = torrentsFragment
                    true
                }
                else -> false
            }
        }
    }
}