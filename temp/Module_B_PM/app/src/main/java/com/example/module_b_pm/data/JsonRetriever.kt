package com.example.module_b_pm.data

import android.content.Context
import com.example.module_b_pm.R
import org.json.JSONObject

data class Skill(val imagePath: String, val name: String, val description: String)
data class Category(val name: String, val skills: List<Skill>)
class JsonRetriever(private val context: Context) {
    fun getSkills(): List<Category> {
        val extractedCategories = mutableListOf<Category>()
        val data = JSONObject(context.resources.openRawResource(R.raw.skills).bufferedReader().readText())
val categories =         data.getJSONArray("categories")
        for(x in 0 until categories.length()) {
            val o = categories.getJSONObject(x)
            val name = o.getString("name")
            val skills = o.getJSONArray("skills")
            val extractedSkills = mutableListOf<Skill>()
            for(c in 0 until skills.length()) {
                val co = skills.getJSONObject(c)
                extractedSkills.add(Skill(imagePath = "", name = co.getString("name"), description = co.getString("description")))

            }
            extractedCategories.add(Category(name, skills = extractedSkills))



        }
return extractedCategories
    }
}