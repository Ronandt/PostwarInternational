package com.example.worldskillsandroidkit.Practice

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class API {
    suspend fun Thing(): String {
        val URL = URL("https://testing.com")
        return withContext(Dispatchers.IO) {
            with(URL.openConnection() as HttpURLConnection) {
                try {
                    setRequestProperty("Content-Type", "application/json")
                    requestMethod = "POST"
                   outputStream.write("{}".toByteArray())
                   inputStream.bufferedReader().readText()


                } catch (e: Exception) {
                    errorStream.bufferedReader().readText()
                }

            }
        }

    }
}