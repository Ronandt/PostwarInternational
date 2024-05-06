package com.example.stroopy

import android.content.Context
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import org.json.JSONArray
import java.io.File

class SaveData(private val context: Context) {
    var sharedPreferences = context.getSharedPreferences("leaderboard", Context.MODE_PRIVATE)
    var currentData = context.getSharedPreferences("session", Context.MODE_PRIVATE)
    val moshi = Moshi.Builder().build()
    @OptIn(ExperimentalStdlibApi::class)
    fun insertUser(user: User) {
        var getInitialData = sharedPreferences.getString("json", JSONArray().toString())
        val adapter = moshi.adapter<MutableList<User>>()
        var data = getInitialData?.let { adapter.fromJson(it) }
        data?.add(user)
        with(sharedPreferences.edit()) {
            this.putString("json",adapter.toJson(data) )
            apply()
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun displayUsers(): MutableList<User>? {
        var initial = sharedPreferences.getString("json", JSONArray().toString())
        val adapter = moshi.adapter<MutableList<User>>()
        return initial?.let { adapter.fromJson(it) }

    }

    @OptIn(ExperimentalStdlibApi::class)
    fun insertCurrentSession(user: User) {
        val adapter = moshi.adapter<User>()
        with(currentData.edit()) {
            this.putString("session", adapter.toJson(user))
            apply()
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun retrieveCurrentSession(): User? {
        val adapter = moshi.adapter<User>()
        val current = currentData.getString("session", "{}")
        var user: User? = current?.let { adapter.fromJson(it) }
        return user
    }
}

@JsonClass(generateAdapter = true)
data class User(
    val name:String,
    val image: String,
    val score: Int
)