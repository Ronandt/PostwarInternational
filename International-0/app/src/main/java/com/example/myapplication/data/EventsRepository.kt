package com.example.myapplication.data

import android.content.Context
import com.example.myapplication.R
import org.json.JSONArray
import org.json.JSONObject

class EventsRepository(val context: Context) {
    private val eventsData = JSONArray(context.resources.openRawResource(R.raw.events_data).bufferedReader().readText())
    private val db = Db.initialise(context)
    @OptIn(ExperimentalStdlibApi::class)
    suspend fun changeEventStatus(id: Int) {
        if(!db.eventStatusDao().exists(id)) {
            db.eventStatusDao().insert(listOf(EventRead(id = id)))
        }



    }

    @OptIn(ExperimentalStdlibApi::class)
    suspend fun retrieveEvents(): List<Event> {
        val events = mutableListOf<Event>()
        for( x in 0..<eventsData.length()) {
            val jsonObject = eventsData.getJSONObject(x)
            events.add(Event(title = jsonObject.getString("title"), text = jsonObject.getString("text"), id = jsonObject.getInt("id"),  db.eventStatusDao().exists(jsonObject.getInt("id"))))
        }
        return events.toList()
    }

    suspend fun retrieveEvent(id: Int): Event{
        var event = eventsData.getJSONObject(id)
        return Event(title =  event.getString("title"), text = event.getString("text"), id = event.getInt("id"),  db.eventStatusDao().exists(event.getInt("id")))
    }

    suspend fun increaseViews(eventViews: EventViews) {
        db.eventViewsDao().update(eventViews)
    }



}

data class Event(var title: String, var text: String, var id: Int, var read: Boolean)