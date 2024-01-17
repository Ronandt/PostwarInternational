package com.example.international_a_am.data

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.international_a_am.R
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter


@JsonClass(generateAdapter = true)
@Entity
data class Event(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String,
    var status: String? = "Unread",
    var viewCount: Int = 0
)

class JsonRetriever(private val context: Context) {

    val moshi = Moshi.Builder().build()
    val db = Database.getDb(context)

    @OptIn(ExperimentalStdlibApi::class)
    suspend fun retrieveEvents(): List<Event>? {
        if(db?.eventDao()?.selectAll()?.size == 0) {
            var eventJsonAdapter = moshi.adapter<List<Event>>()
            val rawJson = context.resources.openRawResource(R.raw.events_data).bufferedReader().readText()

            eventJsonAdapter.fromJson(rawJson)?.let { db.eventDao().insertAll(it) }
            return db?.eventDao()?.selectAll()

        }
        return db?.eventDao()?.selectAll()



    }
}