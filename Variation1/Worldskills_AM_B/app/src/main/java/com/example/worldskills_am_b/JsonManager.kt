package com.example.worldskills_am_b

import android.content.Context
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import org.w3c.dom.Comment

class JsonManager(private val context: Context) {
    val moshi = Moshi.Builder().build()

    @OptIn(ExperimentalStdlibApi::class)
    fun photos(): List<Photo>? {
        val adapter = moshi.adapter<List<Photo>>()
        var data = adapter.fromJson(context.resources.openRawResource(R.raw.photos).bufferedReader().readText())
        return data

    }

    @OptIn(ExperimentalStdlibApi::class)
    fun skills(): List<Category>? {
        val adapter = moshi.adapter<List<Category>>()
        var data = context.resources.openRawResource(R.raw.skillsandcategory).bufferedReader().readText()
        var realData = adapter.fromJson(data)
        return realData

    }

    @OptIn(ExperimentalStdlibApi::class)
    fun videos(): List<Video>? {
        val adapter = moshi.adapter<List<Video>>()
        var data = context.resources.openRawResource(R.raw.videos).bufferedReader().readText()
                    var realData = adapter.fromJson(data)
        return realData
    }

}

@JsonClass(generateAdapter = true)
data class Photo(
    val imageLink: String,
    val votes: Int,
    val popularity: Int

)

@JsonClass(generateAdapter = true)
data class Video(
    val id: String,
    val title: String,
    val thumbnail: String,
    val link: String,
    val comments: List<CommentJ>,
)

@JsonClass(generateAdapter = true)
data class CommentJ(
    val ipAddress: String,
    val text: String
)

@JsonClass(generateAdapter = true)
data class Category(

    val category: String,
    val trades: List<Skill>
)


@JsonClass(generateAdapter = true)
data class Skill(
    val skillName: String,
    val imageLink: String? = null,
    val text: String
)