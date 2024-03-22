package com.example.worldskillsandroidkit

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.text.Text

class MyAppReceiver(): GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() =  MyAppWidget()
}

class MyAppWidget() : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Text(text = "amazing")
        }
    }
}