package com.rikkimikki.torrentino.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.utils.BASE_SERVER_PORT
import com.rikkimikki.torrentino.utils.BASE_TORRSERVER_PORT
import com.rikkimikki.torrentino.utils.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
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
            if(!checkServer(sharedPreferences.getString(SERVER_IP,LOCAL_IP) ?: LOCAL_IP,BASE_SERVER_PORT))
                searchServer()
            if(!checkServer(sharedPreferences.getString(TORRSERVER_IP,LOCAL_IP) ?: LOCAL_IP,BASE_TORRSERVER_PORT))
                searchServer()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun searchServer(){
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
                    client.connect(InetSocketAddress(prefix + i.toString(), BASE_TORRSERVER_PORT), 200)
                    answerTorrServer.send(i)
                    client.close()
                }catch (_:Exception){}

            }
        }

        for (i in 0..256){
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val client = Socket()
                    client.connect(InetSocketAddress(prefix + i.toString(), BASE_SERVER_PORT), 200)
                    answerServer.send(i)
                    client.close()
                }catch (_:Exception){}

            }
        }


        val server = if(!answerServer.isEmpty) prefix + answerServer.receive() else LOCAL_IP
        if (checkServer(server,BASE_SERVER_PORT))
            sharedPreferences.edit().putString(SERVER_IP,server).apply()


        val torrServer = if(!answerTorrServer.isEmpty) prefix + answerTorrServer.receive() else LOCAL_IP
        if(checkServer(torrServer,BASE_TORRSERVER_PORT))
            sharedPreferences.edit().putString(TORRSERVER_IP,torrServer).apply()
    }

    private fun checkServer(ip:String, port:Int):Boolean{
        try {
            val client = Socket()
            client.connect(InetSocketAddress(ip, port), 150)
            if (client.isConnected) {
                client.keepAlive = true
                client.close()
                toast(getApplication(),getApplication<Application>().getString(R.string.server_find_success)+" "+ip+":"+port)
                return true
            }
            client.close()
        } catch (e: IOException) {
            toast(getApplication(),R.string.failed_server_contact)
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