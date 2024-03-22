package com.example.worldskills

import android.content.Context
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

object   JsonManager {
    val moshi = Moshi.Builder().build()
    @OptIn(ExperimentalStdlibApi::class)
    suspend fun getEvents(context: Context, scope: CoroutineScope): List<Event>? {

        val adapter = moshi.adapter<List<Event>>()
      var data = RealmDb.getDb().query<EventData>().asFlow().map {it.list.toList()}.stateIn(scope)
        val jsonData = adapter.fromJson(context.resources.openRawResource(R.raw.events).bufferedReader().readText())
       if( data.value.size == 0) {
           jsonData?.forEach {
               RealmDb.getDb().write {
             var eventData =   EventData().apply {
                   _id = it.id
                   viewCount = it.viewCount
               }
                   copyToRealm(eventData)
               }
           }
       }



     return jsonData
    }


}

@JsonClass(generateAdapter = true)
data class Event(val id: String, val image: String, val title: String, val text: String, val viewCount: Int, val otherImages: List<String>)


/*
@JsonClass
data class Ticket(val id: String, val image: String, val title: String)*/