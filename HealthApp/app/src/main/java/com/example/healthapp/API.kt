package com.example.healthapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class API() {
    val link = "http://10.0.2.2:50004"

    fun login() {

    }

    suspend fun register():JSONObject {
        return postRequest("/api/authenticate/signup", JSONObject().apply {
            this.put("mberId", "user2")
            this.put("mberPassword", "1234")
            this.put("mberNm", "Mobile man")
        })
    }

    fun getRequest(route: String): JSONObject {
        val url = URL(link + route)
        return JSONObject(url.readText())

    }


    suspend fun postRequest(route: String, data: JSONObject): JSONObject {
        val url = URL(link + route)
       return withContext(Dispatchers.IO) {
            with(url.openConnection() as HttpURLConnection) {
                try {
                    this.requestMethod = "POST"
                    this.setRequestProperty("Content-Type", "multipart/form-data")

                    this.outputStream.write(data.toString().toByteArray())
                    return@with JSONObject(this.inputStream.bufferedReader().readText())
                } catch(e: Exception) {
                    println(e.message)
                    return@with JSONObject(this.errorStream.bufferedReader().readText())
                }

            }
        }

    }

}