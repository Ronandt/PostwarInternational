package com.example.international5

import android.content.Context
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.international5.ui.theme.DarkBlue
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class MyAppWidgetReceiver : GlanceAppWidgetReceiver() {

    // Let MyAppWidgetReceiver know which GlanceAppWidget to use
    override val glanceAppWidget: GlanceAppWidget = MyAppWidget()
}
class MyAppWidget : GlanceAppWidget() {

    fun calculateTimeDifference(startUnixTime: Long, endUnixTime: Long): Pair<Long, Long> {
        val differenceInSeconds = endUnixTime - startUnixTime

        // Calculate days and hours
        val days = TimeUnit.SECONDS.toDays(differenceInSeconds)
        val hours = TimeUnit.SECONDS.toHours(differenceInSeconds) % 24

        return Pair(days, hours)
    }

    @OptIn(ExperimentalMaterialApi::class)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // Load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.

        provideContent {
            // create your AppWidget here
            var timeDifference by remember {
                mutableStateOf(System.currentTimeMillis() /1000)
            }

            LaunchedEffect(key1 = Unit) {
                while (true) {
                    delay(5000)
                    timeDifference = System.currentTimeMillis()/ 1000
                }

            }
            Column(
                modifier = GlanceModifier.fillMaxSize().background(Color.White),
                verticalAlignment = Alignment.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Countdown to close ceremony ", modifier = GlanceModifier.padding(4.dp), style = TextStyle(fontWeight = FontWeight.Medium, color = ColorProvider(
                    DarkBlue
                ), textAlign = TextAlign.Center))
                Row(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = calculateTimeDifference(timeDifference,1708370592L ).first.toString(), modifier = GlanceModifier.padding(4.dp), style = TextStyle(fontWeight = FontWeight.Bold, color = ColorProvider(
                        DarkBlue
                    ), textAlign = TextAlign.Center, fontSize = 18.sp),)
                    Spacer(GlanceModifier.width(10.dp))
                    Text(text = calculateTimeDifference(timeDifference,1708370592L ).second.toString() , modifier = GlanceModifier.padding(4.dp),style = TextStyle(fontWeight = FontWeight.Bold   , color = ColorProvider(
                        DarkBlue
                    ), textAlign = TextAlign.Center, fontSize = 18.sp))

                }
                Row {
                    Text(text = "Days", style  = TextStyle(fontWeight = FontWeight.Bold, color = ColorProvider(
                        DarkBlue
                    )))
                    Spacer(modifier = GlanceModifier.width(10.dp))
                    Text(text = "Hours", style  = TextStyle(fontWeight = FontWeight.Bold, color = ColorProvider(
                        DarkBlue
                    )))

                }
            }

        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun MyContent() {
        Column(
            modifier = GlanceModifier.fillMaxSize().background(Color.White),
            verticalAlignment = Alignment.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Countdown to close ceremony ", modifier = GlanceModifier.padding(12.dp), style = TextStyle(fontWeight = FontWeight.Medium, color = ColorProvider(
                DarkBlue
            )))

            Row {
                Text(text = "Days", style  = TextStyle(fontWeight = FontWeight.Medium, color = ColorProvider(
                    DarkBlue
                )))
                Spacer(modifier = GlanceModifier.width(10.dp))
                Text(text = "Hours", style  = TextStyle(fontWeight = FontWeight.Medium, color = ColorProvider(
                    DarkBlue
                )))

            }


        }
    }
}


