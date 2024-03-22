package edu.ws2022.aXX.pm

import android.content.Context

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter

class JsonManager(private val context: Context) {
    private val moshi = Moshi.Builder().build()
    @OptIn(ExperimentalStdlibApi::class)
    fun getSkillsAndCategory(): List<Category>? {
        var jsonAdapter = moshi.adapter<List<Category>>()
        var resource = context.resources.openRawResource(R.raw.skillsandcategory).bufferedReader().readText()
        var POJOResource = jsonAdapter.fromJson(resource)
        return POJOResource
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun photos(): List<edu.ws2022.aXX.pm.Photo>? {
        var jsonAdapter = moshi.adapter<List<edu.ws2022.aXX.pm.Photo>>()
        val resource = context.resources.openRawResource(R.raw.photos).bufferedReader().readText()
        val pojoresource = jsonAdapter.fromJson(resource)
        return pojoresource
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun videos(): List<Video>? {
        var jsonAdapter = moshi.adapter<List<Video>>()
        var resource = context.resources.openRawResource(R.raw.videos).bufferedReader().readText()
        return jsonAdapter.fromJson(resource)
    }
}

@JsonClass(generateAdapter = true)
data class Category(val category: String, val trades: List<Trade>  )

@JsonClass(generateAdapter = true)
data class Trade(val skillName: String, val text: String, val imageLink: String)

@JsonClass(generateAdapter = true)
data class Photo(val imageLink: String, val popularity: Int, val votes: Int)


@JsonClass(generateAdapter = true)
data class Video(val id: String, val title: String, val link: String, val thumbnail: String)