package com.example.international

import android.content.Context
import androidx.compose.runtime.MutableState
import org.json.JSONObject


data class Category(
    val name: String,
    val trades: List<String>
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
}