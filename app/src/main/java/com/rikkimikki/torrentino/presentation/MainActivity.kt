package com.rikkimikki.torrentino.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.databinding.ActivityMainBinding
import com.rikkimikki.torrentino.presentation.ui.controller.ControllerFragment
import com.rikkimikki.torrentino.presentation.ui.films.FilmsContainerFragment
import com.rikkimikki.torrentino.presentation.ui.search.SearchContainerFragment
import com.rikkimikki.torrentino.presentation.ui.torrents.TorrentsContainerFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val controllerFragment = ControllerFragment()
    private val torrentsFragment = TorrentsContainerFragment()
    private val searchFragment = SearchContainerFragment()
    private val filmsFragment = FilmsContainerFragment()
    private lateinit var viewModel: MainViewModel

    var activeFragment: Fragment = filmsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        setupFragments()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.initServers()
    }

    private fun setupFragments() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.mainContainer, filmsFragment, null)
            //add(R.id.mainContainer, torrentsFragment, null).hide(torrentsFragment)
            //add(R.id.mainContainer, searchFragment, null).hide(searchFragment)
            //add(R.id.mainContainer, controllerFragment, null).hide(controllerFragment)
        }.commit()

        binding.bottomNavigationView.setOnItemSelectedListener {
            return@setOnItemSelectedListener when (it.itemId) {
                R.id.navigation_films -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(filmsFragment).commit()
                    activeFragment = filmsFragment
                    true
                }
                R.id.navigation_controller -> {
                    if (controllerFragment.isAdded)
                        supportFragmentManager.beginTransaction().hide(activeFragment).show(controllerFragment).commit()
                    else
                        supportFragmentManager.beginTransaction().hide(activeFragment)
                            .add(R.id.mainContainer,controllerFragment,null).commit()
                    activeFragment = controllerFragment
                    true
                }
                R.id.navigation_search -> {
                    if (searchFragment.isAdded)
                        supportFragmentManager.beginTransaction().hide(activeFragment).show(searchFragment).commit()
                    else
                        supportFragmentManager.beginTransaction().hide(activeFragment)
                            .add(R.id.mainContainer,searchFragment,null).commit()
                    activeFragment = searchFragment
                    true
                }
                R.id.navigation_torrents -> {
                    if (torrentsFragment.isAdded)
                        supportFragmentManager.beginTransaction().hide(activeFragment).show(torrentsFragment).commit()
                    else
                        supportFragmentManager.beginTransaction().hide(activeFragment)
                            .add(R.id.mainContainer,torrentsFragment,null).commit()
                    activeFragment = torrentsFragment
                    true
                }
                else -> false
            }
        }
    }
}