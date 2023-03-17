package com.rikkimikki.torrentino.presentation.ui.films

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.data.ApiFactory
import com.rikkimikki.torrentino.domain.pojo.torrent.Data
import com.rikkimikki.torrentino.domain.pojo.torrent.TorrentResponse
import com.rikkimikki.torrentino.presentation.MainViewModel
import com.rikkimikki.torrentino.utils.SingleLiveData
import com.rikkimikki.torrentino.utils.getAddTorrentBody
import com.rikkimikki.torrentino.utils.parseSearch
import com.rikkimikki.torrentino.utils.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TorrentsViewModel(application: Application) : AndroidViewModel(application){
    private var sharedPreferences: SharedPreferences
    private val disposable = CompositeDisposable()
    private val apiService = ApiFactory.getApiService()

    val torrents = SingleLiveData<TorrentResponse>()
    private val torServerUrl: String = NO_IP
        get() {
            return if (field != NO_IP)
                field
            else if (sharedPreferences.contains(MainViewModel.TORRSERVER_IP))
                sharedPreferences.getString(MainViewModel.TORRSERVER_IP,NO_IP) ?: NO_IP
            else{
                toast(getApplication(), R.string.error)
                NO_IP
            }
        }

    init {
        sharedPreferences =
            application.getSharedPreferences(MainViewModel.PREF_SERVER, Context.MODE_PRIVATE)
    }

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
                toast(getApplication(),R.string.error)
            })
    }

    fun addTorrent(torrent: Data){
        disposable.add(apiService.addTorrent(torServerUrl, getAddTorrentBody(torrent.magnetLink,torrent.poster,torrent.film))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                toast(getApplication(),R.string.added_success)
            }
            ) {
                toast(getApplication(), R.string.error)
            })
    }


    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    companion object{
        private const val NO_IP = "127.0.0.1"
    }
}