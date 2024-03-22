package edu.ws2022.aXX.pm

import android.content.Context

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import edu.ws2022.aXX.pm.theme.DarkBlue

class MyReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() =  MyAppWidget()
}

class MyAppWidget: GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            var unixTime = System.currentTimeMillis()/1000
            var Selectedseconds = 1711312620
            var days by remember { mutableStateOf(((Selectedseconds - unixTime)/3600)/24)}
            var hours by remember {mutableStateOf(((Selectedseconds - unixTime)/3600)%24)}
            androidx.glance.layout.Column(modifier = GlanceModifier.background(Color.White)){
                Text(text = "Countdown to close ceremony", style = TextStyle(textAlign = TextAlign.Center,fontWeight = FontWeight.Bold, color = ColorProvider(
                    DarkBlue
                )))
               Row(modifier = GlanceModifier.background(Color.White)) {

                   Column {
                       Text(text = days.toString(), style = TextStyle(fontWeight = FontWeight.Bold, color = ColorProvider(
                           DarkBlue
                       ), fontSize = 24.sp ))
                       Text(text = "Days", style = TextStyle(fontWeight = FontWeight.Bold, color = ColorProvider(
                           DarkBlue
                       )))
                   }
                   androidx.glance.layout.Spacer(modifier = GlanceModifier.width(10.dp  ))
                   Column{
                        Text(text = hours.toString(), style = TextStyle(fontWeight = FontWeight.Bold, color = ColorProvider(
                            DarkBlue
                        ), fontSize = 24.sp ))
                        Text(text = "Hours", style = TextStyle(fontWeight = FontWeight.Bold, color = ColorProvider(
                            DarkBlue
                        )))

                    }

                }

            }
        }
    }
}