package com.example.worldskillsandroidkit.data

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

@OptIn(ExperimentalStdlibApi::class)
class WorldSkillsAPI(private val apiUrl: String = "https://ln262qn923.execute-api.ap-southeast-1.amazonaws.com/Prod", private val jsonAdapter: Moshi = JsonConverter.adapter) {
    suspend fun getSampleData(): List<SampleData>? {
        val request = getRequest("/results")
        val jsonAdapter = jsonAdapter.adapter<List<SampleData>>()
        return jsonAdapter.fromJson(request.getJSONArray("results").toString())
    }

    suspend fun getRequest(route: String): JSONObject {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL("$apiUrl$route")
                JSONObject(url.readText())
            }
            catch(e:Exception) {
                JSONObject()
            }

        }

    }

    suspend fun postRequest(route: String, jsonObject: JSONObject): JSONObject{
        return withContext(Dispatchers.IO) {
            val url = URL("$apiUrl$route")
            try {
                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "POST"
                    doOutput = true
                    setRequestProperty("Content-Type", "application/json")
                    outputStream.write(jsonObject.toString().toByteArray())
                    return@with JSONObject(inputStream.bufferedReader().readText())

                }
            } catch(e: Exception){
                JSONObject()
            }


        }
    }


}

@JsonClass(generateAdapter = true)
data class SampleData(
    val name: String
)