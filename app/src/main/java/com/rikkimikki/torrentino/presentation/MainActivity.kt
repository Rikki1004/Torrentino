package com.rikkimikki.torrentino.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.data.ApiFactory
import com.rikkimikki.torrentino.databinding.ActivityMainBinding
import com.rikkimikki.torrentino.domain.pojo.torrent.Data
import com.rikkimikki.torrentino.domain.pojo.torrent.TorrentResponse
import com.rikkimikki.torrentino.presentation.ui.controller.ControllerFragment
import com.rikkimikki.torrentino.presentation.ui.films.FilmsContainerFragment
import com.rikkimikki.torrentino.presentation.ui.search.SearchFragment
import com.rikkimikki.torrentino.presentation.ui.torrents.TorrentsFragment
import com.rikkimikki.torrentino.utils.parseSearch
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val controllerFragment = ControllerFragment()
    private val torrentsFragment = TorrentsFragment()
    private val searchFragment = SearchFragment()
    private val filmsFragment = FilmsContainerFragment()

    var activeFragment: Fragment = filmsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragments()



        /*val apiService = ApiFactory.getApiService()

        val disposable = apiService.searchTorrents(
            //"tsrvwc.ru/get_new.php?secret=tsrvwc&hash=&q=search/0/0/000/2/","митчелы")
            "tsrvwc.ru","tsrvwc","","search/0/0/000/2/митчелы")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                parseSearch(it.string())
            }) { throwable ->
                throwable.printStackTrace()
                Toast.makeText(this, "ошибка", Toast.LENGTH_SHORT).show()
            }
*/


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