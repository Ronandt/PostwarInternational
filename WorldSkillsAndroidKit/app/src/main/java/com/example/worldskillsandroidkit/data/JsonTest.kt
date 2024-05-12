package com.example.worldskillsandroidkit.data

import android.content.Context
import com.example.worldskillsandroidkit.R
import com.google.gson.Gson

class JsonTest(private val context: Context) {

    fun convert(): List<Sample> {
        val gson = Gson()
        return gson.fromJson(
            context.resources.openRawResource(R.raw.sample).bufferedReader().readText(), Array<Sample>::class.java
        ).toList()
    }
}


data class Sample(
    val id: Int,
    val name: String?,
    val a: List<A>

)

data class A(
    val a: String
)
