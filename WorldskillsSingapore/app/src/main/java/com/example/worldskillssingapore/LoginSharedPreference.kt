package com.example.worldskillssingapore

import android.content.Context

class LoginSharedPreference(private val context: Context) {
    private val loginSharedPref = context.getSharedPreferences("login", Context.MODE_PRIVATE)
    private val getImagePref = context.getSharedPreferences("image", Context.MODE_PRIVATE)
    fun login(email: String, accountid: String, orgid: String) {
        with(loginSharedPref.edit()) {
            this.putString("email", email)
            this.putString("accountid", accountid   )
            this.putString("orgid", orgid   )
            this.apply()
        }

    }
    fun retrieveLogin(): Login? {
        return loginSharedPref.getString("email", "")
            ?.let { Login(it,
                loginSharedPref.getString("accountid", "")!!, loginSharedPref.getString("orgid", "")!!
            ) }
    }
    fun clearSession() {
        with(loginSharedPref.edit()) {
            this.clear()
            apply()
        }
    }

    fun storeImage(uri: String) {
        with(getImagePref.edit()) {
            putString("image-${retrieveLogin()?.accountid}", uri    )
            apply()
        }
    }

    fun getImage(): String? {
       return getImagePref.getString("image-${retrieveLogin()?.accountid}",  null)
    }
}

data class Login(

    val email: String,
    val accountid:String,
    val orgid: String
)

