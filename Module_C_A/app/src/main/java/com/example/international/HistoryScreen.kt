package com.example.international

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
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

@Composable
fun HistoryScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn() {
            items(6) {

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(0.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier
                            .size(20.dp)
                            .border(shape = CircleShape, color =  Color(0xFF053B69), width = 3.dp)
                            .clip(CircleShape)
                            .border(shape = CircleShape, color =  Color(0xFF053B69), width = 3.dp))
                        Column {
                            Divider( modifier = Modifier
                                .fillMaxHeight(1.2f)
                                .width(3.dp), color =  Color(0xFF053B69))
                        }

                    }
                    Column() {
                        Text(text = "10-15 September 2024", fontWeight = FontWeight.Bold, color = Color.Black, fontSize =  16.sp)
                        Text(text = "Worldskills Lyon", fontWeight = FontWeight.Bold, color = Color(0xFF053B69), fontSize = 20.sp)
                        Row {

                            Image(painter = painterResource(R.drawable.worldskills_two), contentDescription = "Worldskills ", contentScale = ContentScale.Crop)
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(text = "Description \"Description \"Description \"Description \"Description \"Description \"Description ", modifier = Modifier.padding(6.dp))
                        }
                        
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}