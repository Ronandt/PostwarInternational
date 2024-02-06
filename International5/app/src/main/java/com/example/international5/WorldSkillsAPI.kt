package com.example.international5

import android.telephony.data.TrafficDescriptor
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

@OptIn(ExperimentalStdlibApi::class)
class WorldSkillsAPI {
    private val apiUrl = "https://ln262qn923.execute-api.ap-southeast-1.amazonaws.com/Prod"
    private val moshi = Moshi.Builder().build()

    suspend fun getSkill(): List<Category>? {
        val request = getRequest("/getSkillTrade")
        val jsonAdapter = moshi.adapter<List<Category>>()
        return jsonAdapter.fromJson(request.getJSONArray("categories").toString())
    }

    suspend fun getPhotos(): List<PhotoItem>? {
        val request = getRequest("/photos")
        val jsonAdapter = moshi.adapter<List<PhotoItem>>()
        return jsonAdapter.fromJson(request.getJSONArray("photos").toString())
    }

    suspend fun getVideos(): List<VideoItem>? {
        val request = getArrayRequest("/videos")
        println(request)
        val jsonAdapter = moshi.adapter<List<VideoItem>>()
        return jsonAdapter.fromJson(request.toString())
    }

    suspend fun insertComment(comment: CommentItem, videoId: String) {
        val request = postRequest("/addComments", jsonObject = JSONObject().apply {
            this.put("videoId", videoId)
            this.put("comment", JSONObject().apply {
                this.put("ipAddress", comment.ipAddress)
                this.put("text", comment.text)
            })
        })

    }







    suspend fun getRequest(route: String): JSONObject {
        return withContext(Dispatchers.IO) {
            val url = URL("$apiUrl$route")

            return@withContext JSONObject(url.readText())
        }

    }
    suspend fun getArrayRequest(route: String): JSONArray {
        return withContext(Dispatchers.IO) {
            val url = URL("$apiUrl$route")

            return@withContext JSONArray(url.readText())
        }

    }

    suspend fun postRequest(route: String, jsonObject: JSONObject): JSONObject {
        return withContext(Dispatchers.IO) {
            val url = URL("$apiUrl$route")
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "POST"
                doOutput = true
                setRequestProperty("Content-Type", "application/json")
                outputStream.write(jsonObject.toString().toByteArray())
                return@with JSONObject(inputStream.bufferedReader().readText())

            }

        }
    }


}

@JsonClass(generateAdapter = true)
data class Category(
    val category: String,
    val trades: List<Trade>
)


@JsonClass(generateAdapter = true)
data class Trade(
    val skillName: String,
    val imageLink: String,
    val text: String,
)


@JsonClass(generateAdapter = true)
data class PhotoItem(
    val imageLink: String,
    val popularity: String,
    val votes: String
)


@JsonClass(generateAdapter = true)
data class VideoItem(
    val id: String,
    val title: String,
    val link: String,
    val comments: List<CommentItem>,
    val thumbnail: String
)

@JsonClass(generateAdapter = true)
data class CommentItem(
    val ipAddress: String,
    val text: String
)
