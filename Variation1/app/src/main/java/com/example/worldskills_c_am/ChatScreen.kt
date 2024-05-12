package com.example.worldskills_c_am

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ChatScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }, containerColor = Color.DarkGray) {
                Icon(Icons.Default.CheckCircle, contentDescription = "thing", tint = Color.Blue  )

            }
        },
    ) {
        Column(modifier = Modifier
            .fillMaxSize().background(Color.Black)
            .padding(it)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Discover channels", fontWeight = FontWeight.SemiBold, color = Color.LightGray)
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "View all")
                }
            }
            LazyRow(modifier =Modifier, horizontalArrangement = Arrangement.spacedBy(10.dp  )) {
                items(4) {
                    Row(modifier = Modifier.width(280.dp    ), horizontalArrangement = Arrangement.spacedBy(4.dp    )) {
                        Column(modifier = Modifier
                            .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp))
                            .padding(6.dp)) {

                            Text(text = "Whole some pointlss chat 2.0", fontWeight = FontWeight.SemiBold, color = Color.White)
                            Text(text = "Whoelsome and heartwarming", color = Color.LightGray)


                        }
                    }
                }

            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Send, contentDescription = null, tint = Color.LightGray)

                    }
                    Text(text = "Threads", color = Color.LightGray)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.LightGray)

                }

            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp , Alignment.CenterVertically), modifier = Modifier.fillMaxWidth()){
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription ="Lau", modifier = Modifier.clip(
                                    CircleShape) )
                                Text(text = "Welcome to chat", fontWeight = FontWeight.SemiBold, color = Color.White, textAlign = TextAlign.Center)
                                Text(text = "Chat with other people and friends and topics ", color = Color.White , textAlign = TextAlign.Center)
                                Button(onClick = { /*TODO*/ }) {
                                    Icon(imageVector = Icons.Default.Face, contentDescription = "face", tint    = Color.White)
                                    Text(text = "Explore channels", color = Color.White, fontWeight = FontWeight.SemiBold)
                                }
                            }


                        }

                    }
                }

            }

        }
    }

}