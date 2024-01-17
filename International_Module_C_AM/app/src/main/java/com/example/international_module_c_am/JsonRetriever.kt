package com.example.international_module_c_am

import android.content.ClipDescription
import android.content.Context
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import org.w3c.dom.Text
@OptIn(ExperimentalStdlibApi::class)
class JsonRetriever(private val context: Context) {
    val moshi = Moshi.Builder().build()

    fun getNews(): List<News>? {
        var rawJson = context.resources.openRawResource(R.raw.news).bufferedReader().readText()
        val newsAdapter = moshi.adapter<List<News>>()
        return newsAdapter.fromJson(rawJson)


    }

    fun getHistory(): List<History>? {
        var rawJson = context.resources.openRawResource(R.raw.history).bufferedReader().readText()
        val historyAdapter = moshi.adapter<List<History>>()
        return historyAdapter.fromJson(rawJson)
    }

    fun getResult(): List<Country>? {
        var rawJson = context.resources.openRawResource(R.raw.results).bufferedReader().readText()
        val historyAdapter = moshi.adapter<List<Country>>()
        return historyAdapter.fromJson(rawJson)
    }
}

@JsonClass(generateAdapter = true)
data class News(
    val title: String,
    val text: String,
    val description: String
)

@JsonClass(generateAdapter = true)
data class History(
    val date: String,
    val title: String,
    val description: String
)

@JsonClass(generateAdapter = true)
data class Country(
    val country: String,
    val position: Int,
    val medalists: List<Medalist>

)

@JsonClass(generateAdapter = true)
data class Medalist(
    val name: String,
    val trade: String,
    val medal: String
)