package com.example.international_module_c_am

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

    suspend fun getResults(): List<CountryItem>? {
        val request = getRequest("/results")
        val jsonAdapter = moshi.adapter<List<CountryItem>>()
        return jsonAdapter.fromJson(request.getJSONArray("results").toString())
    }


    suspend fun getNews(): List<NewsItem>? {
        val request =  getRequest("/news")
        val jsonAdapter = moshi.adapter<List<NewsItem>>()
        return jsonAdapter.fromJson(request.getJSONArray("news").toString())

    }


    suspend fun getHistory(): List<HistoryItem>? {
        val request = getRequest("/history")
        val jsonAdapter = moshi.adapter<List<HistoryItem>>()
        return jsonAdapter.fromJson(request.getJSONArray("historyEvents").toString())

    }

    suspend fun getRequest(route: String): JSONObject {
        return withContext(Dispatchers.IO) {
            val url = URL("$apiUrl$route")
            return@withContext JSONObject(url.readText())
        }

    }


}


@JsonClass(generateAdapter = true)
data class HistoryItem(
  val  image: String,
    val startDate: String,
    val endDate: String,
    val title: String,
    val description: String
)

@JsonClass(generateAdapter = true)
data class NewsItem(
    val image: String,
    val summary: String,
    val title: String,
    val description: String

)

@JsonClass(generateAdapter = true)
data class CountryItem(val countryImage: String,
    val country: String,
    val gold: List<Competitor>,
    val silver: List<Competitor>,
    val bronze: List<Competitor>,
    val excellence: Int,

    )

@JsonClass(generateAdapter = true)
data class Competitor(
    val avatar: String,
    val competitorName: String,
    val skillName: String
)