package com.example.worldskillsandroidkit.data

import androidx.compose.runtime.Composable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

class SocketManager() {
    suspend fun connect() {
        withContext(Dispatchers.IO) {
            val socket = Socket()
            socket.connect(InetSocketAddress("http://10.0.2.2", 8080), 100)
            socket.getOutputStream().write("lgb".toByteArray())
            println(socket.isConnected)
        }

    }
}