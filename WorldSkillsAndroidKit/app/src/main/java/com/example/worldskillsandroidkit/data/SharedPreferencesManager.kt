package com.example.worldskillsandroidkit.data

import android.content.Context
import android.content.SharedPreferences


class SharedPreferencesManager(private val context: Context) {
    private val commonSharedPreference = context.getSharedPreferences("common", Context.MODE_PRIVATE)
    fun  storePreferences(key: String, value: String) {
        with(commonSharedPreference.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun get(key: String): String? {
       return commonSharedPreference.getString(key, null)
    }



}