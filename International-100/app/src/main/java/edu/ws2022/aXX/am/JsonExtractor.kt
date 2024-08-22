package edu.ws2022.aXX.am

import android.content.Context
import com.google.gson.Gson

class JsonExtractor(private val context: Context) {
    val gson = Gson()
    fun extractEvents(): List<EventJson> {
     return  gson.fromJson(  extractData(R.raw.events), Array<EventJson >::class.java).toList()
    }
    private fun extractData(resource: Int ): String {
        val resources = context.resources.openRawResource(resource).bufferedReader().readText()
        return resources
    }
}

data class EventJson(
    val id: String,
    val title: String,
    val image: String,
    val text: String,
    val viewCount: Int,
    val otherImages: List<String>
)
