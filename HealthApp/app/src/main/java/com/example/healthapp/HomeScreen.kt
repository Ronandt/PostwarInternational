package com.example.healthapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.healthapp.ui.theme.Dark
import com.example.healthapp.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("MY health data", color = Color.White, fontWeight = FontWeight.Bold) }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Dark), actions = {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription ="Vert", tint = Color.White )

        })}
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(14.dp)) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Hello competitor", fontSize = 20.sp)

            Card(modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp  )) {
                    Icon(painter = painterResource(R.drawable.man_fill0_wght400_grad0_opsz24), contentDescription ="Man", modifier = Modifier.size(80.dp  ))
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp )) {
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp   )) {
                            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "182.5", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                                Text(text = "Cm")
                            }

                            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "182.5", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                                Text(text = "Kg")
                            }

                            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "182.5", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                                Text(text = "Bmi")
                            }
                        }
                        Spacer(modifier = Modifier
                            .background(
                                brush = Brush.linearGradient(
                                    listOf(
                                        Color.Blue,
                                        Color.Green,
                                        Color.Yellow,
                                        Orange,
                                        Color.Red
                                    )
                                )
                            )
                            .width(160.dp)
                            .height(5.dp))
                    }



                }
            }

            Text(text = "Steps", fontSize = 23.sp)
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(
                            text = "2584",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                        Text(text = "/5000 steps")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "394", color = Color.White, modifier = Modifier.background(Color.Black))

                        Box {
                            Spacer(modifier = Modifier
                                .width(100.dp)
                                .height(7.dp)
                                .background(Color.LightGray))
                            Spacer(modifier = Modifier
                                .width(50.dp)
                                .height(7.dp)
                                .background(Color.Green))
                        }

                    }
                }
            }

            Text(text = "Heart rate", fontSize = 23.sp)
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Row {
                        Image(painter = painterResource(R.drawable.heart_rate_svgrepo_com), contentDescription = "Heart",
                            Modifier
                                .size(50.dp)
                                .background(Color.White, shape = CircleShape))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "144 bpm ",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp, color = Color.Black
                            )
                            Text(text = "/5000 steps", modifier = Modifier
                                .padding(10.dp)
                                .background(Color.Black), Color.White)
                        }
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "394", color = Color.White, modifier = Modifier.background(Color.Black))

                        Box {
                            Spacer(modifier = Modifier
                                .width(100.dp)
                                .height(7.dp)
                                .background(Color.LightGray))
                            Spacer(modifier = Modifier
                                .width(50.dp)
                                .height(7.dp)
                                .background(Color.Green))
                        }

                    }
                }
            }


            Text(text = "Foods", fontSize = 23.sp)
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Image(painter = painterResource(R.drawable.food_dinner_svgrepo_com), contentDescription = "Heart",
                        Modifier
                            .size(50.dp)
                            .background(Color.White, shape = CircleShape))
                    Image(painter = painterResource(R.drawable.breakfast), contentDescription ="", contentScale = ContentScale.Crop, modifier = Modifier.size(50.dp )  )
                    Image(painter = painterResource(R.drawable.breakfast), contentDescription ="", contentScale = ContentScale.Crop, modifier = Modifier.size(50.dp )  )
                    Image(painter = painterResource(R.drawable.breakfast), contentDescription ="", contentScale = ContentScale.Crop, modifier = Modifier.size(50.dp )  )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "MOre vert" )
                    }
                }
            }





            Text(text = "Water", fontSize = 23.sp)
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(R.drawable.water_drop_fill0_wght400_grad0_opsz24), contentDescription = "Heart",
                        Modifier
                            .size(50.dp)
                            .background(Color.White, shape = CircleShape))

                    Column {
                        Text(text = "250/2500ml")
                        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(8.dp  )) {
                            Text(text = "Background", color = Color.White, modifier = Modifier
                                .padding(5.dp)
                                .background(Color.Black))
                            Text(text = "Background", color = Color.White, modifier = Modifier
                                .padding(10.dp)
                                .background(Color.Black))
                        }
                    }
                    Row {
                        Icon(painter = painterResource(R.drawable.cup_svgrepo_com), contentDescription = "Cup", modifier = Modifier.size(50.dp  ))
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "MOre vert" )
                        }
                    }

                }
            }

        }


    }
    }

