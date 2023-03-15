package com.rikkimikki.torrentino.presentation.ui.films

import androidx.lifecycle.ViewModel
import com.rikkimikki.torrentino.data.ApiFactory
import com.rikkimikki.torrentino.domain.pojo.category.Category
import com.rikkimikki.torrentino.domain.pojo.film.PreFilm
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Film
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.FilmInfoResponse
import com.rikkimikki.torrentino.domain.pojo.tvSerie.TvSerieInfoResponce
import com.rikkimikki.torrentino.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoriesViewModel : ViewModel(){
    private val disposable = CompositeDisposable()
    private val apiService = ApiFactory.getApiService()
    private val categories = SingleLiveData<List<Category>>()

    val films = SingleLiveData<List<PreFilm>>()
    val film = SingleLiveData<Film>()
    val tvSerie = SingleLiveData<TvSerieInfoResponce>()

    fun getCategories():SingleLiveData<List<Category>>{
        disposable.add(apiService.getCategories(getCategoriesBody(), "MovieListCategory")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { categoryResponse ->
                    categories.postValue(categoryResponse.getCategories())
                },
                { throwable ->
                    println(throwable.toString())
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
                { throwable ->
                    println(throwable.toString())
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
                { throwable ->
                    println(throwable.toString())
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
                { throwable ->
                    println(throwable.toString())
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
                { throwable ->
                    println(throwable.toString())
                }))
    }
}