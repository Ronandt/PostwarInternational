package com.example.international

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp, vertical = 10.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Mock data", color = Color.Gray, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Image(painter = painterResource(R.drawable.medals), contentDescription = "Medals", contentScale = ContentScale.Crop, modifier = Modifier.height(35.dp))
            }

        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(10) {
                Card(modifier = Modifier.height(80.dp), onClick  = {
                    navController.navigate("resultDetail")
                }) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Image(painter = painterResource(R.drawable.korea), contentDescription = "Korea")

                            Column {
                                Text(text = "Korea", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 18.sp)

                                Text(text = "#1", fontWeight = FontWeight.Bold, color =Color(0xFF053B69), fontSize = 20.sp)

                            }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(text = "1")
                            Text(text = "1")
                            Text(text = "1")
                            Text(text = "1")
                        }
                    }
                }
            }

        }

    }

}