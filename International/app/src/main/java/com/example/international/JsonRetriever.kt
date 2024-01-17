package com.example.international

import android.content.Context
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
@OptIn(ExperimentalStdlibApi::class)
class JsonRetriever( private val context: Context) {
    val moshi = Moshi.Builder().build()

    fun statistic(): List<Statistic>? {
        var rawJson = context.resources.openRawResource(R.raw.statistic).bufferedReader().readText()
        val jsonAdapter = moshi.adapter<List<Statistic>>()
        var statistic = jsonAdapter.fromJson(rawJson)
        return statistic


    }

    fun skill(): List<Skill>? {
        val rawJson = context.resources.openRawResource(R.raw.skills).bufferedReader().readText()
        val jsonAdapter = moshi.adapter<List<Skill>>()
        var skill = jsonAdapter.fromJson(rawJson)
        return skill
    }

    fun about(): List<About>? {
        val rawJson = context.resources.openRawResource(R.raw.about).bufferedReader().readText()
        val jsonAdapter = moshi.adapter<List<About>>()
        val about = jsonAdapter.fromJson(rawJson)
        return about
    }
}

@JsonClass(generateAdapter = true)
data class Statistic(
    val event: String,
    val competitors: Int

)

@JsonClass(generateAdapter = true)
data class Skill(
    val name: String,
    val description: String,
)

@JsonClass(generateAdapter = true)
data class About(
    val name: String,
    val description: String
)