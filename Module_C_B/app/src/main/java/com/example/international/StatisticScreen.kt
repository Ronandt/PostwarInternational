package com.example.international

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international.ui.theme.DarkBlue
import com.example.international.ui.theme.DarkerBlue
import com.example.international.ui.theme.LightBlue

@Composable
fun StatisticScreen(navController: NavController) {
    val events =  remember { mutableStateListOf<Event>() }
    val context = LocalContext.current
    val jsonRetreiver = JsonRetriever(context)
    LaunchedEffect(key1 = Unit) {
        events.addAll(jsonRetreiver.retrieveStatistic())
        events.reverse()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        Header(text = "Statistic")
        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(DarkBlue)
            .padding(20.dp)) {
            Text(color= Color.White, text = "The numbers of WorldSkills competitors", fontWeight = FontWeight.Bold, modifier = Modifier.align(CenterHorizontally), fontSize = 26.sp)
            Spacer(modifier = Modifier.height(40.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterHorizontally)) {
                repeat(events.size) {
                    Column(modifier = Modifier, verticalArrangement = Arrangement.Center) {

                        Text(text = events[it].competitors.toString(), color = Color.White, modifier = Modifier.align(CenterHorizontally))
                        Box(modifier = Modifier
                            .height(460.dp)
                            .width(30.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(DarkerBlue)) {
                            Spacer(modifier = Modifier
                                .align(BottomCenter)
                                .height(200.dp.div(500) * events[it].competitors)
                                .width(30.dp)
                                .background(LightBlue))
                        }
                        Text(text = events[it].name, color = Color.White, modifier = Modifier
                            .width(100.dp)
                            .align(Alignment.CenterHorizontally), textAlign = TextAlign.Center)
                    }
                }

            }
            Spacer(modifier = Modifier.weight(1f))
            Image(painter = painterResource(R.drawable.decoration), contentDescription = "Decoration", modifier = Modifier.fillMaxWidth().offset(y = 16.dp), contentScale = ContentScale.Crop,)


        }
    }


}