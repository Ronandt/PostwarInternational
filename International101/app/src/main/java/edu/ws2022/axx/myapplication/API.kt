package edu.ws2022.axx.myapplication

import androidx.room.PrimaryKey
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import org.w3c.dom.Comment

enum class Method {
    GET,
    POST,
    DELETE,
    PATCH
}
class API() {
    val client = OkHttpClient()
    val gson = Gson()
    private val mainUrl = "10.0.2.2:3000"
    suspend fun photos() : List<Photo>{
       return gson.fromJson( makeRequest(mainUrl + "/photos", method = Method.GET), Array<Photo>::class.java).toList()

    }
    suspend fun skillsAndCategory(): List<Category> {
        return gson.fromJson(JSONObject(makeRequest(mainUrl + "/skillsAndCategory", method = Method.GET)).getJSONArray("categories").toString(), Array<Category>::class.java).toList()

    }
    suspend fun videos(): List<Video> {
        return gson.fromJson(makeRequest(mainUrl + "/videos", method = Method.GET), Array<Video>::class.java).toList()
    }



    suspend fun makeRequest(url: String, method: Method, body: RequestBody = "".toRequestBody()): String? {
        return withContext(Dispatchers.IO  ) {

            val request = Request.Builder().url(url).method(method.toString(),if(method == Method.GET) null else body).build()
            with(client.newCall(request).execute()) {
                return@with this.body?.string()
            }
        }
    }



}


data class Photo(
    val imageLink: String,
    val popularity: Int,
    val votes: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0


    )

data class Category(
    val category: String,
    val trades: List<Trade>,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)
data class Trade(
    val skillName: String,
    val imageLink: String,
    val text: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)

data class Video(

    val title: String,
    val thumbnail: String,
    val comments: List<Comment>,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)

data class Comment(
    val ipAddress: String,
    val text: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)
