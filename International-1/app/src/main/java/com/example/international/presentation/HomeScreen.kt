package com.example.international.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val cardList = remember { listOf("skills", "photos", "videos")}
    Column(modifier = Modifier.fillMaxSize()) {
        Column() {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .offset(y = -70.dp)
                .background(Color.Black)
                ) {

                    Text(text = "Banner")
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter), horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally), ) {
                        repeat(3) {
                            Spacer(modifier = Modifier
                                .size(20.dp).clip(CircleShape)
                                .background(Color.White)
                                )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }


            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)) {
                cardList.forEach { it ->
                    Card(onClick = {}, modifier = Modifier.size(300.dp, 150.dp)) {
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                            Text(text = it.uppercase(), fontSize = 30.sp, fontWeight = FontWeight.Bold)
                        }


                    }
                }
            }


        }

    }
}