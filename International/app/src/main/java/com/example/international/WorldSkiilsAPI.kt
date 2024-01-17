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

    suspend fun getSkills(): List<SkillsItem>? {
        val request = getRequest("/skills")
        val jsonAdapter = moshi.adapter<List<SkillsItem>>()
        return jsonAdapter.fromJson(request.getJSONArray("skills").toString())
    }


    suspend fun getStatistics(): List<StatisticsItem>? {
        val request =  getRequest("/statistics")
        val jsonAdapter = moshi.adapter<List<StatisticsItem>>()
        return jsonAdapter.fromJson(request.getJSONArray("statistics").toString())

    }


    suspend fun getAbout(): List<AboutItem>? {
        val request = getRequest("/about")
        var aboutList = mutableListOf<AboutItem >()
        var abouts = listOf("Inspire", "Develop", "Influence")
        abouts.forEach {
            val rawObject = request.getJSONObject("about").getJSONObject(it)
            aboutList.add(AboutItem(it, rawObject.getString("description")))
        }
        return aboutList
        println(aboutList)


    }

    suspend fun getRequest(route: String): JSONObject {
        return withContext(Dispatchers.IO) {
            val url = URL("$apiUrl$route")
            return@withContext JSONObject(url.readText())
        }

    }


}


@JsonClass(generateAdapter = true)
data class StatisticsItem(
        val competitionName: String,
    val numberOfCompetitors: Int
        )


@JsonClass(generateAdapter = true)
data class AboutItem(
    val title: String?,
    val description: String
)


@JsonClass(generateAdapter = true)
data class SkillsItem(
    val name: String,
    val description: String,
    val image: String
)

