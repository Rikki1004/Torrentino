package com.rikkimikki.torrentino.presentation.ui.films

import android.R
import android.app.Application
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.rikkimikki.torrentino.data.ApiFactory
import com.rikkimikki.torrentino.domain.pojo.category.Category
import com.rikkimikki.torrentino.domain.pojo.torrent.TorrentResponse
import com.rikkimikki.torrentino.utils.SingleLiveData
import com.rikkimikki.torrentino.utils.parseSearch
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TorrentsViewModel(application: Application) : AndroidViewModel(application){
    private val disposable = CompositeDisposable()
    private val apiService = ApiFactory.getApiService()

    val torrents = SingleLiveData<TorrentResponse>()


    fun getTorrents(name: String) {
        val disposable = apiService.searchTorrents(
            "tsrvwc.ru","tsrvwc","", "search/0/0/000/2/$name"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                torrents.postValue(parseSearch(it.string()))
            }) { throwable ->
                throwable.printStackTrace()
                Toast.makeText(getApplication(), "ошибка", Toast.LENGTH_SHORT).show()
            }
    }


    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}