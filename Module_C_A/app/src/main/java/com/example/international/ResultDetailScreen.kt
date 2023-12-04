package com.example.international

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultDetailScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()

        .verticalScroll(rememberScrollState())) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigateUp()}) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back", tint =   Color(0xFF053B69), modifier = Modifier.size(34.dp))

            }
            Text(text = "Results", fontWeight = FontWeight.SemiBold)


        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .background(Color.LightGray), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(R.drawable.korea), contentDescription = "Results")
            Text(text = "Korea", fontWeight = FontWeight.Bold, color = Color(0xFF053B69))


        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
           ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically,) {
                Image(painterResource(R.drawable.gold), contentDescription = "Gold", modifier = Modifier.size(30.dp))
                Text(text = "Gold Medals", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

            }
            LazyRow {

                items(5) {
                    Card(modifier = Modifier
                        .size(180.dp, 220.dp)
                        .padding(10.dp)) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Image(painter = painterResource(id = R.drawable.male), contentDescription = "theodare shankels ", contentScale = ContentScale.Crop, modifier = Modifier
                                .clip(
                                    RoundedCornerShape(14.dp)
                                )
                                .fillMaxWidth()
                                .height(100.dp))
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Theodore Shankles", modifier = Modifier.basicMarquee(), fontWeight = FontWeight.Bold)
                            Text(text = "Plubming and heating", modifier = Modifier.basicMarquee(), color = Color.DarkGray)
                        }

                    }
                }

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically,) {
                Image(painterResource(R.drawable.silver), contentDescription = "Silver", modifier = Modifier.size(30.dp))
                Text(text = "Silver Medals", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

            }

            LazyRow {
                items(5) {
                    Card(modifier = Modifier
                        .size(180.dp, 220.dp)
                        .padding(10.dp)) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Image(painter = painterResource(id = R.drawable.male), contentDescription = "theodare shankels ", contentScale = ContentScale.Crop, modifier = Modifier
                                .clip(
                                    RoundedCornerShape(14.dp)
                                )
                                .fillMaxWidth()
                                .height(100.dp))
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Theodore Shankles", modifier = Modifier.basicMarquee(), fontWeight = FontWeight.Bold)
                            Text(text = "Plubming and heating", modifier = Modifier.basicMarquee(), color = Color.DarkGray)
                        }

                    }
                }

            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically,) {
                Image(painterResource(R.drawable.bronze), contentDescription = "Bronze", modifier = Modifier.size(30.dp))
                Text(text = "Bronze", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

            }
            LazyRow {
                items(5) {
                    Card(modifier = Modifier
                        .size(180.dp, 220.dp)
                        .padding(10.dp)) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Image(painter = painterResource(id = R.drawable.male), contentDescription = "theodare shankels ", contentScale = ContentScale.Crop, modifier = Modifier
                                .clip(
                                    RoundedCornerShape(14.dp)
                                )
                                .fillMaxWidth()
                                .height(100.dp))
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Theodore Shankles", modifier = Modifier.basicMarquee(), fontWeight = FontWeight.Bold)
                            Text(text = "Plubming and heating", modifier = Modifier.basicMarquee(), color = Color.DarkGray)
                        }

                    }
                }

            }

        }

    }
}