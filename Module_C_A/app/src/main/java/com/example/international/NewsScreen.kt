package com.example.international

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(12.dp)) {

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            items(8) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp), onClick = {
                        navController.navigate("newsDetail")
                }) {
                    Row {
                        Image(painter = painterResource(id = R.drawable.skills_change), contentDescription = "Image", contentScale = ContentScale.Crop, modifier = Modifier
                            .fillMaxHeight()
                            .width(120.dp)
                            .clip(
                                RoundedCornerShape(14.dp, 0.dp, 14.dp, 0.dp)
                            ))
                        Column(modifier = Modifier.padding(8.dp).fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.Start) {

                            Text(text = "Skills Change worlds", color = Color(0xFF053B69), fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis, fontSize = 18.sp  )
                            Text(text = "We've taken everything our", overflow = TextOverflow.Ellipsis, maxLines = 1, color= Color.Gray)
                            
                        }
                    }
                }
            }

        }

    }

}