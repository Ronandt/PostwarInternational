package com.example.international.data

import android.content.Context
import com.example.international.R
import com.example.international.data.DB.db
import org.json.JSONArray

class Repository(val context: Context) {
    suspend fun retrieveEvents(): List<Event> {
        var json = JSONArray(context.resources.openRawResource(R.raw.events_data).bufferedReader().readText())
        val db = DB.getDatabase(context)
        if(!db?.eventDao()?.exists()!!) {
            for(x in 0 until json.length()) {
                val o = json.getJSONObject(x)
                db.eventDao().insertALl(listOf(Event( title = o.getString("title"), text = o.getString("text"), readStatus = "Unread", viewCount = 0, image1 = "", image2 = "", image3 = "")))
            }
        }
        return db.eventDao().selectAll()


    }
}