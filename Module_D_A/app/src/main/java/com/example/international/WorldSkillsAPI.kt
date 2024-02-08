package com.example.international

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

@OptIn(ExperimentalStdlibApi::class)
class WorldSkillsAPI {
    private val apiUrl = "https://ln262qn923.execute-api.ap-southeast-1.amazonaws.com/Prod"
    private val moshi = Moshi.Builder().build()

    suspend fun getNewsInfo(): List<CategoryItem>? {
        val request = getRequest("/newsInfo")
        val jsonAdapter = moshi.adapter<List<CategoryItem>>()
        return jsonAdapter.fromJson(request.getJSONArray("tradeCategories").toString())
    }


    suspend fun getRequest(route: String): JSONObject {
        return withContext(Dispatchers.IO) {
            val url = URL("$apiUrl$route")
            return@withContext JSONObject(url.readText())
        }

    }


}


@JsonClass(generateAdapter = true)
data class CategoryItem(
    val category: String,
    val subcategories: List<SubCategory>,

)

@JsonClass(generateAdapter = true)
data class SubCategory(
    val subcategory: String,
    val newsItems: List<NewsItem>
)

@JsonClass(generateAdapter = true)
data class NewsItem(
    val title: String,
    val time: String,
    val description: String
)
