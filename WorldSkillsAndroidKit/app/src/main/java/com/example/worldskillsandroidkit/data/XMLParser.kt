package com.example.worldskillsandroidkit.data

import android.content.Context
import android.util.Log
import androidx.compose.ui.text.capitalize
import com.example.worldskillsandroidkit.R
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.util.Locale

enum class XMLTags {
    Location,
    Parameter,
    Value
}

class XMLParser(private val context: Context) {
    fun parse() {
        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val newParser = factory.newPullParser()
        newParser.setInput(context.resources.openRawResource(R.raw.response).bufferedReader())
        var eventType = newParser.eventType
        val currentParameters = mutableListOf<Parameter>()
        var currentObject: Parameter? = null
        val currentWeatherPoint = WeatherPoint("", "")
        while(eventType != XmlPullParser.END_DOCUMENT) {

            if(currentParameters.isNotEmpty()) {
                currentObject = currentParameters.last()
            }

           if(eventType == XmlPullParser.START_TAG) {
               when(newParser.name.lowercase()) {
                    "location" -> {
                       currentObject?.lat = newParser.getAttributeValue(0).toFloat()
                       currentObject?.lon = newParser.getAttributeValue(1).toFloat()
                   }
                    "parameter" -> {
                       currentParameters.add(Parameter(parameterName = newParser.getAttributeValue(0)))
                   }
                   "value" -> {
                       currentWeatherPoint.date = newParser.getAttributeValue(0)
                   }
               }

            }
           else if(eventType == XmlPullParser.TEXT) {

               if(newParser.text.isNotBlank()) {
                   currentWeatherPoint.value = newParser.text
                   println(currentWeatherPoint)
                   currentObject?.data?.add(currentWeatherPoint.copy())
               }


           }
            eventType =  newParser.next();
        }
    }

}


//model
data class Parameter(
    var parameterName: String,
    var lat: Float =  0.0f,
    var lon: Float  = 0.0f,
    var data: MutableList<WeatherPoint> = mutableListOf()
)

data class WeatherPoint(
    var date: String,
    var value: String
)


