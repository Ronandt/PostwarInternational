package com.example.worldskillsa_am_2

import android.content.Context
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList

object JsonManager {
    val moshi = Moshi.Builder().build()
    @OptIn(ExperimentalStdlibApi::class)
    suspend fun getEvents(context: Context): List<EventData>? {
        var adapter = moshi.adapter<List<Event>>()
        var jsonData = adapter.fromJson(context.resources.openRawResource(R.raw.events).bufferedReader().readText())
        var database = RealmDb.getDb()
        if(database.query<EventData>().count().find() == 0L) {
            database.write {
                jsonData?.forEach {
                    this.copyToRealm(EventData(

                    ).apply {
                        this.id = it.id
                        this.image = it.image
                        this.text = it.text
                        this.title =  it.title
                        this.viewCount = it.viewCount
                        this.otherImages = it.otherImages.toRealmList()


                    })
                }

            }
        }

        var queryData = database.query<EventData>().find().toList()
        return queryData

    }
}


@JsonClass(generateAdapter = true)
data class Event(
    val id: String,
    val image: String,
    val title: String,
    val text: String,
    var viewCount: Int,
    val otherImages: List<String>

)