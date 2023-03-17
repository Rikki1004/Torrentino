package com.rikkimikki.torrentino.presentation.ui.films

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.rikkimikki.torrentino.data.ApiFactory
import com.rikkimikki.torrentino.domain.pojo.torrent.Data
import com.rikkimikki.torrentino.domain.pojo.torrent.TorrentResponse
import com.rikkimikki.torrentino.utils.SingleLiveData
import com.rikkimikki.torrentino.utils.getAddTorrentBody
import com.rikkimikki.torrentino.utils.parseSearch
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TorrentsViewModel(application: Application) : AndroidViewModel(application){
    private val disposable = CompositeDisposable()
    private val apiService = ApiFactory.getApiService()

    val torrents = SingleLiveData<TorrentResponse>()
    val server = "127.0.0.1"


    fun getTorrents(name: String) {
        disposable.add(apiService.searchTorrents(
            "tsrvwc.ru","tsrvwc","", "search/0/0/000/2/$name"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                torrents.postValue(parseSearch(it.string()))
            }) { throwable ->
                throwable.printStackTrace()
                Toast.makeText(getApplication(), "ошибка", Toast.LENGTH_SHORT).show()
            })
    }

    fun addTorrent(torrent: Data){
        disposable.add(apiService.addTorrent(server, getAddTorrentBody(torrent.magnetLink,torrent.poster,torrent.film))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ addTorrentResponse ->
                Toast.makeText(getApplication(),addTorrentResponse.title +" успешно добавлен", Toast.LENGTH_SHORT).show()
            }
            ) { throwable ->
                throwable.printStackTrace()
                Toast.makeText(getApplication(),"Ошибка добавления торрента", Toast.LENGTH_SHORT).show()
            })
    }


    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}