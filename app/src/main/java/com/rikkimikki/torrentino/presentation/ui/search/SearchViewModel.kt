package com.rikkimikki.torrentino.presentation.ui.search

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.rikkimikki.torrentino.data.ApiFactory
import com.rikkimikki.torrentino.domain.pojo.search.Movie__1
import com.rikkimikki.torrentino.utils.LIBRIA_BASE_URL
import com.rikkimikki.torrentino.utils.SingleLiveData
import com.rikkimikki.torrentino.utils.getSearchBody
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
                    Toast.makeText(getApplication(), "Ничего не найдено", Toast.LENGTH_SHORT).show()
                }
            }) { throwable ->
                throwable.printStackTrace()
                Toast.makeText(getApplication(), "ошибка", Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(getApplication(), "Ничего не найдено", Toast.LENGTH_SHORT).show()
                }
            }) { throwable ->
                throwable.printStackTrace()
                Toast.makeText(getApplication(), "ошибка", Toast.LENGTH_SHORT).show()
            })
    }

    //https://api.anilibria.tv/v2/searchTitles?search=cудьба апокреф

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}