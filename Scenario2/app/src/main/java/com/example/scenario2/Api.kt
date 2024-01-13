package com.example.scenario2

import org.json.JSONObject
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL

object Api {

    suspend fun login(mobile: String):  JSONObject {

        val url = URL("http://10.0.2.2:3000/api/wss/userLogin")
        with(url.openConnection() as HttpURLConnection) {
            try {
                val json = JSONObject().apply {
                    this.put("mobile", mobile)
                }
                doOutput = true
                requestMethod= "POST"
                setRequestProperty("Content-Type", "application/json")
                val outputStream = DataOutputStream(getOutputStream())
                outputStream.write(json.toString().toByteArray())
                outputStream.flush()
                outputStream.close()
                return JSONObject(inputStream.bufferedReader().use{it.readText()})
            }
            catch (e: Exception) {
                return JSONObject(errorStream.bufferedReader().readText())
            }


        }
    }
    suspend fun test() {
        println(URL("http://10.0.2.2:3000").readText())
    }




}