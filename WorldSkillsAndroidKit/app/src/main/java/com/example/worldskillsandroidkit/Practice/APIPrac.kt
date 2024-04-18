package com.example.worldskillsandroidkit.Practice

import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class APIManager() {
 fun postReq(url:String, json: JSONObject): JSONObject {
     var url = URL(url)
     with(url.openConnection() as HttpURLConnection) {
         this.requestMethod = "POST"
         try {
             this.outputStream.write(json.toString().toByteArray())
             return JSONObject(this.inputStream.bufferedReader().readText())

         }
         catch (e: Exception) {
             println(e.message)
             println(errorStream.toString())
             return JSONObject()
         }
     }

 }

    fun getReq(url: String): JSONObject {
        var url  = URL(url)
        return JSONObject(url.readText())

    }
}