@file:OptIn(ExperimentalStdlibApi::class)

package com.example.pocketvault

import android.content.Context
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import org.json.JSONArray

class PasswordData(private val context: Context) {
    val moshi = Moshi.Builder().build()
    val sessionPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
    val userPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    val passwordPreferences = context.getSharedPreferences("password", Context.MODE_PRIVATE)
    val currentSessionAccessor =   passwordPreferences.getString("passwords-${getSession()}", "[]")

    fun login(username: String, password: String): Boolean {
        if(!userPreferences.contains(username)) {
            return false
        }

        if(userPreferences.getString(username, null) == password) {
            with(sessionPreferences.edit()) {
                this.putString("session",username)
                apply()
            }
            return true
        } else {
            return false
        }

    }

    fun clearSession() {
        with(sessionPreferences.edit()) {
            this.clear()
            apply()
        }
    }

    fun getSession(): String?{
        return sessionPreferences.getString("session", null)
    }

    fun register(username: String, password: String) {

        with(userPreferences.edit()) {
           this.putString(username, password)
            apply()
        }
    }

    fun putPasswords(password: Password) {
        preloadData()
        val adapter = moshi.adapter<MutableList<Password>>()
        with(passwordPreferences.edit()) {
          var data =   adapter.fromJson(      currentSessionAccessor)

            data?.add(password)
            this.putString("passwords-${getSession()}", adapter.toJson(data))
            apply()
        }
    }

    fun putPasswordss(passwords: List<Password>) {
        preloadData()
        val adapter = moshi.adapter<MutableList<Password>>()
        with(passwordPreferences.edit()) {
            var data =   adapter.fromJson(      currentSessionAccessor)

            data?.addAll(passwords)
            this.putString("passwords-${getSession()}", adapter.toJson(data))
            apply()
        }
    }
    fun getPasswords(): MutableList<Password>? {
        preloadData()
        val adapter = moshi.adapter<MutableList<Password>>()
        return adapter.fromJson(currentSessionAccessor)

    }

    fun deleteData(index: Int) {
       var p =  getPasswords()
        with(passwordPreferences.edit()) {
            p?.removeAt(index)
            this.putString("passwords-${getSession()}", moshi.adapter<List<Password>>().toJson(p))
            apply()
        }
    }

    fun editData(index: Int, password: Password) {
        var p = getPasswords()
        with(passwordPreferences.edit()) {
            p?.set(index, password)
            this.putString("passwords-${getSession()}", moshi.adapter<List<Password>>().toJson(p))
            apply()
        }
    }

    fun preloadData() {
        var rawData = context.resources.openRawResource(R.raw.passwords).bufferedReader().readText()
        val adapter = moshi.adapter<List<Password>>()
        val modifiedData = adapter.fromJson(rawData)

        with(passwordPreferences.edit()) {
            var rawValue = passwordPreferences.getString("passwords-${getSession()}", "[]")
            if(JSONArray(rawValue).length() == 0) {
                this.putString("passwords-${getSession()}",   adapter.toJson(modifiedData))

            }

            apply()
        }
    }


}


@JsonClass(generateAdapter = true)
data class User(
    val username: String,
    val password: String,

)


@JsonClass(generateAdapter = true)
data class Password(
    val title: String,
    val username: String,
    val password: String,
    val website: String
)
