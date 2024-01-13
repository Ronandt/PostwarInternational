package com.example.international5

import android.content.Context
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import org.json.JSONObject

class JsonRetriever(private val context: Context) {
    private val moshi = Moshi.Builder().build()
    @OptIn(ExperimentalStdlibApi::class)
    fun skills(): List<SkillType>? {
        var rawJson = context.resources.openRawResource(R.raw.skills).bufferedReader().readText()

       val jsonAdapter =  moshi.adapter<List<SkillType>>()
       return  jsonAdapter.fromJson(JSONObject(rawJson).getJSONArray("skillCategories").toString())
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun photos(): List<Photo>? {
        var rawJson = context.resources.openRawResource(R.raw.photos).bufferedReader().readText()
        val jsonAdapter = moshi.adapter<List<Photo>>()
        return jsonAdapter.fromJson(rawJson)
    }
}

@JsonClass(generateAdapter = true)
data class SkillType(
    val categoryName: String,
    val skills: List<Skill>
)

@JsonClass(generateAdapter = true)
data class Skill(
    val name: String,
    val description: String
)


@JsonClass(generateAdapter = true)
data class Photo(
    val votes: Int,
    val popularity: Int
)