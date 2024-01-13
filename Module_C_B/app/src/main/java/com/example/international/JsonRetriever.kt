package com.example.international

import android.content.Context
import org.json.JSONObject


class JsonRetriever(private val context: Context) {
    fun retrieveStatistic(): MutableList<Event> {
        val events = mutableListOf<Event>()
        val json = JSONObject(context.resources.openRawResource(R.raw.statistic).bufferedReader().readText())
        val worldskillsevents = json.getJSONArray("worldSkillsEvents")
        for(x in 0 until worldskillsevents.length()) {
            var o = worldskillsevents.getJSONObject(x)
            events.add(Event(name = o.getString("eventName"), competitors = o.getInt("competitorsParticipated")))
        }
        return events
    }
}

data class Event(
    val name: String,
    val competitors: Int
)