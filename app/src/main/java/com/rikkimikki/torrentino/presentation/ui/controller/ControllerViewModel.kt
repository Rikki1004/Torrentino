package com.rikkimikki.torrentino.presentation.ui.controller

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.rikkimikki.torrentino.data.ApiFactory
import com.rikkimikki.torrentino.domain.pojo.category.Category
import com.rikkimikki.torrentino.domain.pojo.server.GetPositionResponse
import com.rikkimikki.torrentino.presentation.MainViewModel.Companion.PREF_SERVER
import com.rikkimikki.torrentino.presentation.MainViewModel.Companion.SERVER_IP
import com.rikkimikki.torrentino.utils.SingleLiveData
import com.rikkimikki.torrentino.utils.getControllerBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ControllerViewModel(application: Application) : AndroidViewModel(application) {
    private var sharedPreferences: SharedPreferences
    private val disposable = CompositeDisposable()
    private val apiService = ApiFactory.getApiService()
    private val serverUrl: String = NO_IP
    get() {
        return if (field != NO_IP)
            field
        else if (sharedPreferences.contains(SERVER_IP))
            sharedPreferences.getString(SERVER_IP,NO_IP) ?: NO_IP
        else{
            Toast.makeText(getApplication(), "err", Toast.LENGTH_SHORT).show()
            NO_IP
        }
    }


    val volume = SingleLiveData<Int>()
    val position = SingleLiveData<GetPositionResponse>()

    init {
        sharedPreferences =
            application.getSharedPreferences(PREF_SERVER, Context.MODE_PRIVATE)
    }
    fun getVolume() {
        disposable.add(apiService.getVolume(serverUrl, getControllerBody("getVolumeJson"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { volumeResponse ->
                    volume.value = volumeResponse.volume
                },
                { throwable ->
                    println(throwable.toString())
                }))
    }

    fun getPosition() {
        disposable.add(apiService.getPosition(serverUrl, getControllerBody("getPosition"))
            .subscribeOn(Schedulers.io())
            .repeatWhen{completed -> completed.delay(1, TimeUnit.SECONDS)}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { positionResponse ->
                    position.value = positionResponse
                },
                { throwable ->
                    println(throwable.toString())
                }))
    }

    fun sendControls(control: String,position: String="") {
        disposable.add(apiService.playTorrent(serverUrl, getControllerBody(control,position))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
    }

    companion object{
        private const val NO_IP = "127.0.0.1"
    }
}