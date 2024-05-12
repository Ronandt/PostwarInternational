package com.example.worldskillssingapore

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class APIManager() {
    val url = "http://10.0.2.2:3000"
    val moshi = Moshi.Builder().build()

    suspend fun postRequest(route: String, jsonObject: JSONObject): JSONObject {
        val url = URL(url + route)
        return withContext(Dispatchers.IO) {

            with(url.openConnection() as HttpURLConnection) {
                try {
                    this.requestMethod = "POST"
                    setRequestProperty("Content-Type", "application/json")
                    outputStream.write(jsonObject.toString().toByteArray())
                    return@with JSONObject(inputStream.bufferedReader().readText())
                } catch (e: Exception) {
                    return@with JSONObject(errorStream.bufferedReader().readText())

                }

            }
        }


    }


    @OptIn(ExperimentalStdlibApi::class)
    suspend fun login(email: String, password: String, accesskey: String): LoginData? {
        val adapter = moshi.adapter<LoginData>()


        val result = postRequest("/api/wss/userLogin", jsonObject = JSONObject().apply {
            this.put("email", email)
            this.put("password", password)
            this.put("accesskey", accesskey)
        })

        return adapter.fromJson(result.toString())



    }


    @OptIn(ExperimentalStdlibApi::class)
    suspend fun changePassword(accountid:String, oldpwd: String, newpwd: String, cnfmpwd: String, accesskey: String): ChangePasswordData? {
        val adapter = moshi.adapter<ChangePasswordData>()
        val result = postRequest("/api/wss/changePassword", jsonObject = JSONObject().apply {
            this.put("accountid", accountid)
            this.put("oldpwd", oldpwd )
            this.put("newpwd", newpwd)
            this.put("cnfmpwd", cnfmpwd)
            this.put("accesskey", accesskey)

        })

        return adapter.fromJson(result.toString())

    }


    @OptIn(ExperimentalStdlibApi::class)
    suspend fun getFoodCourts(): List<FoodCourt>? {
        val adapter = moshi.adapter<List<FoodCourt>>()

       val result = getRequest("/api/ecommerce/getLocations")
       return adapter.fromJson(result.getJSONObject("result").getJSONArray("data").toString() )


    }

    @OptIn(ExperimentalStdlibApi::class)
    suspend fun getMerchants(ownercode: String, accesskey: String): List<Merchants>? {
        val adapter = moshi.adapter<List<Merchants>>()

        val result = getRequest("/api/ecommerce/getMerchant/$ownercode/$accesskey")
        return adapter.fromJson(result.getJSONObject("result").getJSONArray("data").toString())

    }

    @OptIn(ExperimentalStdlibApi::class)
    suspend fun getFood(merchantcode: String, accesskey: String): List<Food>? {
        val adapter = moshi.adapter<List<Food>>()
        val result = getRequest("/api/ecommerce/getMenuItemByMerchant/$merchantcode/$accesskey")
        return adapter.fromJson(result.getJSONObject("result").getJSONArray("data").toString())

    }
    suspend fun getRequest(route: String): JSONObject {
        return withContext(Dispatchers.IO) {
            val result = URL(url + "${route}").readText()
            return@withContext JSONObject(result)
        }

    }
}


@JsonClass(generateAdapter = true)
data class LoginData(
    val status:String,
    val result: LoginResult

)

@JsonClass(generateAdapter = true)
data class LoginResult(

    val message: String?,
    val accountid: String?,
    val email: String?,
    val orgid: String?
)


@JsonClass(generateAdapter = true)
data class ChangePasswordData(
    val status: String,
    val result: ChangePasswordResult,


)


@JsonClass(generateAdapter = true)
data class ChangePasswordResult(
    val message: String?,
)




@JsonClass(generateAdapter = true)
data class  FoodCourt(
    val name: String,
    val ownercode: String,
    val lat: Float,
    val lng: Float

)

@JsonClass(generateAdapter = true)
data class Merchants(
    val id: String,
    val name: String,
    val merchantcode: String

)

@JsonClass(generateAdapter = true)
data class Food(
    val name: String,
    val id: Int,
    val calorie: String,
    val color: String,
    val description: String,
    val price: String

)
