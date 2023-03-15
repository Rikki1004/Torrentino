package com.rikkimikki.torrentino.presentation.ui.films

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.rikkimikki.torrentino.data.ApiFactory
import com.rikkimikki.torrentino.domain.pojo.category.Category
import com.rikkimikki.torrentino.domain.pojo.film.PreFilm
import com.rikkimikki.torrentino.utils.SingleLiveData
import com.rikkimikki.torrentino.utils.getCategoriesBody
import com.rikkimikki.torrentino.utils.getFilmsFromCategoryBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CategoriesViewModel : ViewModel(){
    private val disposable = CompositeDisposable()
    private val apiService = ApiFactory.getApiService()
    private val categories = SingleLiveData<List<Category>>()

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

    fun getFilms(category: Category, offset:Int = 0):SingleLiveData<List<PreFilm>>{
        val films = SingleLiveData<List<PreFilm>>()
        disposable.add(apiService.getFilms(getFilmsFromCategoryBody(category,offset), "MovieDesktopListPage")
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
}