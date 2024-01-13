package com.example.international

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import kotlinx.coroutines.delay

@Composable
fun StatisticScreen(navController: NavController) {
    val context = LocalContext.current
    var statistic = remember    {JsonRetriever(context).statistic()}

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 20.dp)
        .padding(end = 20.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(800.dp)
            .background(Color(0xFF003764)), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "The number of WorldSkills Competitors", fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(vertical = 20.dp), fontSize = 22.sp)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(50.dp, alignment = Alignment.CenterHorizontally)) {
                repeat(statistic?.size!!) {
                    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically)) {
                        var currentSize by remember {mutableStateOf(0.dp)}
                        var competitors by remember {mutableStateOf(0)}
                        var calc =  400.dp.div(1300)
                        LaunchedEffect(Unit) {
                            delay(1500L)
                            currentSize = (calc * statistic[it].competitors)
                            (1..statistic[it].competitors)  .forEach {
                                delay(1L)
                                competitors += 1
                            }

                        }
                        Text(text = competitors.toString(), color = Color.White)
                        Box(contentAlignment = Alignment.BottomCenter) {

                            var width = 26.dp

                            Spacer(modifier = Modifier
                                .height(400.dp)
                                .width(width)
                                .background(Color(0xFF00223D)))
                            Spacer(modifier = Modifier
                                .height(currentSize)
                                .width(width).animateContentSize()
                                .background(
                                    Color(
                                        0xFF0BA4EC
                                    )
                                ).animateContentSize())

                        }

                        Text(text =  statistic[it].event, color = Color.White, modifier = Modifier.width(100.dp), textAlign = TextAlign.Center)
                    }
                }


            }
            Spacer(modifier = Modifier.weight(1f))
            Image(painterResource(R.drawable.wrapper), modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.Crop, contentDescription = "")
        }
    }
}