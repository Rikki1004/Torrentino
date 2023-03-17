package com.rikkimikki.torrentino.presentation.ui.films

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.data.ApiFactory
import com.rikkimikki.torrentino.domain.pojo.anime.AnimeResponse
import com.rikkimikki.torrentino.domain.pojo.category.Category
import com.rikkimikki.torrentino.domain.pojo.film.PreFilm
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Film
import com.rikkimikki.torrentino.domain.pojo.tvSerie.TvSerieInfoResponce
import com.rikkimikki.torrentino.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoriesViewModel(application: Application) : AndroidViewModel(application){
    private val disposable = CompositeDisposable()
    private val apiService = ApiFactory.getApiService()
    private val categories = SingleLiveData<List<Category>>()

    var films = SingleLiveData<List<PreFilm>>()
    val film = SingleLiveData<Film>()
    val tvSerie = SingleLiveData<TvSerieInfoResponce>()
    val anime = SingleLiveData<AnimeResponse>()

    fun getCategories():SingleLiveData<List<Category>>{
        disposable.add(apiService.getCategories(getCategoriesBody(), "MovieListCategory")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { categoryResponse ->
                    categories.postValue(categoryResponse.getCategories())
                },
                {
                    toast(getApplication(),R.string.error)
                }))
        return categories
    }

    fun getFilms(genre: String):SingleLiveData<List<PreFilm>>{
        val films = SingleLiveData<List<PreFilm>>()
        disposable.add(apiService.getFilms(getFilmsFromCategoryBody(genre,0), "MovieDesktopListPage")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { filmsResponse ->
                    films.postValue(filmsResponse.data.movieListBySlug.movie.preFilms)
                },
                {
                    toast(getApplication(),R.string.error)
                }))
        return films
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun getFilmsWithOffset(genre: String, offset: Int) {
        disposable.add(apiService.getFilms(getFilmsFromCategoryBody(genre,offset), "MovieDesktopListPage")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { filmsResponse ->
                    films.postValue(filmsResponse.data.movieListBySlug.movie.preFilms)
                },
                {
                    toast(getApplication(),R.string.error)
                }))
    }

    fun getFilm(id:Int){
        disposable.add(apiService.getFilmInfo (getFilmsBody(id), "FilmBaseInfo")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { filmResponse ->
                    film.postValue(filmResponse.data.film)
                },
                {
                    toast(getApplication(),R.string.error)
                }))
    }
    fun getTvSerie(id:Int){
        disposable.add(apiService.getTvSerieInfo (getTvSerieBody(id), "TvSeriesBaseInfo")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { tvSerieResponse ->
                    tvSerie.postValue(tvSerieResponse)
                },
                {
                    toast(getApplication(),R.string.error)
                }))
    }

    fun getAnime(id: Int) {
        disposable.add(apiService.getAnimeInfo(LIBRIA_BASE_URL, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ searchResponse ->
                val data = searchResponse

                anime.postValue(data)
            }) {
                toast(getApplication(),R.string.error)
            })
    }

}