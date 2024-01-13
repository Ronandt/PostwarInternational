package com.example.testbed

import android.content.Context
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter

@JsonClass(generateAdapter = true)
data class Amazing(
    val id: String
)

@OptIn(ExperimentalStdlibApi::class)
class Test(private val context: Context) {
    val moshi: Moshi = Moshi.Builder().build()

    fun getAmazing(): List<Amazing>? {
        val rawJson = context.resources.openRawResource(R.raw.amazing).bufferedReader().readText()
        val jsonAdapter = moshi.adapter<List<Amazing>>()
        return jsonAdapter.fromJson(rawJson)
    }
}