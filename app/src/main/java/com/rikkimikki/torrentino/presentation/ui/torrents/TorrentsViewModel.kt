package com.rikkimikki.torrentino.presentation.ui.torrents

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.rikkimikki.torrentino.data.ApiFactory
import com.rikkimikki.torrentino.domain.pojo.server.GetStoreResponse
import com.rikkimikki.torrentino.presentation.MainViewModel
import com.rikkimikki.torrentino.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TorrentsViewModel(application: Application) : AndroidViewModel(application){
    private var sharedPreferences: SharedPreferences
    private val disposable = CompositeDisposable()
    private val apiService = ApiFactory.getApiService()
    private val torServerUrl: String = NO_IP
        get() {
            return if (field != NO_IP)
                field
            else if (sharedPreferences.contains(MainViewModel.TORRSERVER_IP))
                sharedPreferences.getString(
                    MainViewModel.TORRSERVER_IP,
                    NO_IP
                ) ?: NO_IP
            else{
                Toast.makeText(getApplication(), "err", Toast.LENGTH_SHORT).show()
                NO_IP
            }
        }
    private val serverUrl: String = NO_IP
        get() {
            return if (field != NO_IP)
                field
            else if (sharedPreferences.contains(MainViewModel.SERVER_IP))
                sharedPreferences.getString(
                    MainViewModel.SERVER_IP,
                    NO_IP
                ) ?: NO_IP
            else{
                Toast.makeText(getApplication(), "err", Toast.LENGTH_SHORT).show()
                NO_IP
            }
        }

    var torrentsList = SingleLiveData<List<GetStoreResponse>>()
    var needLaunchActivity = SingleLiveData<Intent>()

    init {
        sharedPreferences =
            application.getSharedPreferences(MainViewModel.PREF_SERVER, Context.MODE_PRIVATE)
    }
    fun getTorrents(): SingleLiveData<List<GetStoreResponse>> {
        disposable.add(apiService.getStore(torServerUrl,getTorrentsBody())
            .subscribeOn(Schedulers.io())
            .repeatWhen{completed -> completed.delay(2, TimeUnit.SECONDS)}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { torrentResponse ->
                    torrentsList.postValue(torrentResponse)
                },
                { throwable ->
                    println(throwable.toString())
                }))
        return torrentsList
    }

    fun deleteTorrent(hash:String) {
        disposable.add(apiService.remTorrent(torServerUrl,getDeleteTorrentsBody(hash))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
    }

    fun playTorrentRemote(title:String, id:Int,hash: String){
        disposable.add(
            apiService.playTorrent(serverUrl,getPlayTorrentBody(title, id,torServerUrl,hash))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Toast.makeText(getApplication(), "Воспроизведение скоро начнется", Toast.LENGTH_SHORT).show()
                    },
                    { throwable ->
                        Toast.makeText(getApplication(), throwable.toString(), Toast.LENGTH_SHORT).show()
                    }))
    }

    fun playTorrentLocal(id:Int,hash: String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(
            Uri.parse("http://" + torServerUrl + ":8090/stream/fname?index=" + id + "&play&save&link=" + hash),
            "video/*"
        )
        needLaunchActivity.value = intent
    }


    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
    companion object{
        private const val NO_IP = "127.0.0.1"
    }
}