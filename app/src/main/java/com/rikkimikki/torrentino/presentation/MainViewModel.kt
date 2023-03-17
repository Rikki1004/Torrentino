package com.rikkimikki.torrentino.presentation

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rikkimikki.torrentino.utils.SingleLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.io.IOException
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

class MainViewModel(application: Application):AndroidViewModel(application) {
    private var sharedPreferences = getApplication<Application>().getSharedPreferences(
        PREF_SERVER,
        Context.MODE_PRIVATE
    )

    fun initServers(){
        viewModelScope.launch(Dispatchers.IO) {
            if(!checkServer(sharedPreferences.getString(SERVER_IP,LOCAL_IP) ?: LOCAL_IP,8091))
                searchServer()
            if(!checkServer(sharedPreferences.getString(TORRSERVER_IP,LOCAL_IP) ?: LOCAL_IP,8090))
                searchServer()
        }
    }

    private suspend fun searchServer(){
        //viewModelScope.launch(Dispatchers.IO) {
            //Get local ip
            var ip: List<String>
            DatagramSocket().use { socket ->
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002)
                ip = socket.localAddress.hostAddress?.split(".") as MutableList<String>
            }
            if (ip[0] == "::")
                return

            val prefix = ip[0] + "." + ip[1] + "." + ip[2] + "."

            val answerServer = Channel<Int>()
            val answerTorrServer = Channel<Int>()

            for (i in 0..256){
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val client = Socket()
                        client.connect(InetSocketAddress(prefix + i.toString(), 8090), 200)
                        answerTorrServer.send(i)
                        client.close()
                    }catch (e:Exception){}

                }
            }

            for (i in 0..256){
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val client = Socket()
                        client.connect(InetSocketAddress(prefix + i.toString(), 8091), 200)
                        answerServer.send(i)
                        client.close()
                    }catch (e:Exception){}

                }
            }

            val server = prefix+answerServer.receive()
            val torrServer = prefix+answerTorrServer.receive()

            if (checkServer(server,8091))
                sharedPreferences.edit().putString(SERVER_IP,server).apply()

            if(checkServer(torrServer,8090))
                sharedPreferences.edit().putString(TORRSERVER_IP,torrServer).apply()
        }
    //}

    private fun checkServer(ip:String, port:Int):Boolean{
        try {
            val client = Socket()
            client.connect(InetSocketAddress(ip, port), 150)
            if (client.isConnected) {
                client.keepAlive = true
                client.close()
                return true
            }
            client.close()
        } catch (e: IOException) {
            println("no")
        }
        return false
    }

    companion object{
        const val PREF_SERVER = "PREF_SERVER"
        const val SERVER_IP = "SERVER_IP"
        const val TORRSERVER_IP = "TORRSERVER_IP"
        const val LOCAL_IP = "127.0.0.1"
    }
}