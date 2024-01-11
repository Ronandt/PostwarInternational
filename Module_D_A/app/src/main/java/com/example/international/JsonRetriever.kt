package com.example.international

import android.content.Context
import androidx.compose.runtime.MutableState
import org.json.JSONObject


data class Category(
    val name: String,
    val trades: List<String>
)

data class News(
    val title: String,
    val skillName: String,
    val datetime: String,
    val description: String
)

class JsonRetriever(private val context: Context) {
     fun retrieve(): MutableList<Category> {
         var categories: MutableList<Category> = mutableListOf()
         val jsonObject = JSONObject(context.resources.openRawResource(R.raw.json_data).bufferedReader().readText())
         for(x in 0 until jsonObject.getJSONArray("tradeCategories").length() ) {
             var trades: MutableList<String> = mutableListOf()
             var o = jsonObject.getJSONArray("tradeCategories").getJSONObject(x)
             val name = o.getString("category")
             for(c in 0 until o.getJSONArray("subcategories").length()) {
                 trades.add(o.getJSONArray("subcategories").getJSONObject(c).getString("subcategory"))
             }
             categories.add(Category(name, trades))
         }
         return categories
     }

    fun retrieveNews() : MutableList<News> {
        var news: MutableList<News> = mutableListOf()
        val jsonObject = JSONObject(context.resources.openRawResource(R.raw.news).bufferedReader().readText())
        val jsonArr = jsonObject.getJSONArray("news")
        for(x in 0 until jsonArr.length()) {
            val o = jsonArr.getJSONObject(x)
            news.add(News(o.getString("title"), o.getString("skill_name"), o.getString("datetime"), o.getString("description")))
        }
        return news
    }
}