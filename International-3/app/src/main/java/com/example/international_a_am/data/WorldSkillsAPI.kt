package com.example.international_a_am.data

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

@OptIn(ExperimentalStdlibApi::class)
class WorldSkillsAPI {
    private val apiUrl = "https://ln262qn923.execute-api.ap-southeast-1.amazonaws.com/Prod"
    private val moshi = Moshi.Builder().build()

    suspend fun getEvents(): List<EventItem>? {
        val request = getArrayRequest("/events")
        val jsonAdapter = moshi.adapter<List<EventItem>>()
        return jsonAdapter.fromJson(request.toString())
    }


    suspend fun getTickets(): List<TicketItem>? {
        val request =  getArrayRequest("/tickets")
        val jsonAdapter = moshi.adapter<List<TicketItem>>()
        return jsonAdapter.fromJson(request.toString())

    }

    suspend fun updateViewCount(id: String) {
        val request = getRequest("/updateViewCount/$id")

    }

    suspend fun createTicket(ticketItem: TicketItem) {
        val jsonAdapter = moshi.adapter<TicketItem>()
        val json: JSONObject = JSONObject(jsonAdapter.toJson(ticketItem))
        postRequest("/createTicket", json)


    }




    suspend fun getRequest(route: String): JSONObject {
        return withContext(Dispatchers.IO) {
            val url = URL("$apiUrl$route")

            return@withContext JSONObject(url.readText())
        }

    }
    suspend fun getArrayRequest(route: String): JSONArray {
        return withContext(Dispatchers.IO) {
            val url = URL("$apiUrl$route")

            return@withContext JSONArray(url.readText())
        }

    }

    suspend fun postRequest(route: String, jsonObject: JSONObject): JSONObject{
        return withContext(Dispatchers.IO) {
            val url = URL("$apiUrl$route")
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "POST"
                doOutput = true
                setRequestProperty("Content-Type", "application/json")
                outputStream.write(jsonObject.toString().toByteArray())
                return@with JSONObject(inputStream.bufferedReader().readText())

            }

        }
    }


}

@JsonClass(generateAdapter = true)
data class EventItem(
    val id: String,
    val title: String,
    val image: String,
    val text: String,
    val viewCount: Int,
    val otherImages: List<String>,
    val status: String? = "Unread"
)

@JsonClass(generateAdapter = true)
data class TicketItem(
    val id: String,
    val name: String,
    val imageLink: String,
    val ticketType: String,
    val seat: String,
    val time: String

)



