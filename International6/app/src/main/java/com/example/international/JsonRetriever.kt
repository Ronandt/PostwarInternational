package com.example.international

import android.content.Context
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter

class JsonRetriever(private val context: Context) {
    private val moshi = Moshi.Builder().build()

    @OptIn(ExperimentalStdlibApi::class)
    fun retrieveCategory(): List<Category>? {
        var jsonAdapter = moshi.adapter<List<Category>>()
        var rawJson = context.resources.openRawResource(R.raw.news).bufferedReader().readText()
        return jsonAdapter.fromJson(rawJson)
    }
}


@JsonClass(generateAdapter = true)
data class Category(
    val category: String,
    val skills: List<Skill>
)

@JsonClass(generateAdapter = true)
data class Skill(
    val name: String,
    val newsItems: List<News>
)

@JsonClass(generateAdapter = true)
data class News(
    val newsTitle: String,
    val skillName: String,
    val newsDatetime: String,
)