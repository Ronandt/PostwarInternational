package com.example.automationtest

import android.content.Context
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import org.json.JSONObject

class ExtractJsonBro(private val context: Context) {
    val moshi = Moshi.Builder().build()
    @OptIn(ExperimentalStdlibApi::class)
    fun getNews(): List<News>? {
        val rawData = context.resources.openRawResource(R.raw.newsinfo )

        val adapter = moshi.adapter<List<News>>()
       // println(JSONObject(rawData.bufferedReader().readText()).getJSONArray("tradeCategories").toString())


        return adapter.fromJson(JSONObject(rawData.bufferedReader().readText()).getJSONArray("tradeCategories").toString())

    }
}



@JsonClass(generateAdapter = true)
data class News(
    var category: String?


)