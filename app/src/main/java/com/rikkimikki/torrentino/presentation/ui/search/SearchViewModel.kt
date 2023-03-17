package com.rikkimikki.torrentino.presentation.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.data.ApiFactory
import com.rikkimikki.torrentino.domain.pojo.search.Movie__1
import com.rikkimikki.torrentino.utils.LIBRIA_BASE_URL
import com.rikkimikki.torrentino.utils.SingleLiveData
import com.rikkimikki.torrentino.utils.getSearchBody
import com.rikkimikki.torrentino.utils.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val disposable = CompositeDisposable()
    private val apiService = ApiFactory.getApiService()
    val films = SingleLiveData<List<Movie__1>>()

    fun search(q: String) {
        disposable.add(apiService.getSeacrhRequest(getSearchBody(q), "SuggestSearch")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ searchResponse ->
                val data = searchResponse.getData()
                if (data.isNotEmpty()) {
                    films.postValue(data)
                }else{
                    toast(getApplication(),R.string.nothing_found)
                }
            }) {
                toast(getApplication(),R.string.error)
            })
    }

    fun searchAnime(q: String) {
        disposable.add(apiService.searchAnime(LIBRIA_BASE_URL, q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ searchResponse ->
                val data = searchResponse.list.map { it.toMovie() }

                if (data.isNotEmpty()) {
                    films.postValue(data)
                }else{
                    toast(getApplication(),R.string.nothing_found)
                }
            }) {
                toast(getApplication(),R.string.error)
            })
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}