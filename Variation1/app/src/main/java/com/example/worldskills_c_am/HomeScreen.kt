package com.example.worldskills_c_am

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
Column(modifier = Modifier
    .fillMaxWidth()
    .padding(horizontal = 10.dp)) {
    LazyColumn {
        items(3) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black).padding(10.dp), verticalArrangement = Arrangement.spacedBy(12.dp )) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp   )) {
                    Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "launcher background", modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(16.dp))
                    Text(text = "r/NoStupidQuestions", color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = "12h | 3.5m views", color = Color.DarkGray)
                }
                Text(
                    text = "How much do 4H2 matter in Uni and Scholarship applications?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp, color = Color.White

                )
                Text(text = "Background: I want to drop H2 math to H12 math really abd I was wondering how much 4H2s really affect Uni applications to overseas Unis and scholarships and if H3 can supplement the thing and I don't know what youa re takling abotu ", color = Color.LightGray, maxLines = 3, overflow = TextOverflow.Ellipsis)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                   Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(10.dp  )) {
                       Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.padding(0.dp  ).height(42.dp  ).border(brush = Brush.horizontalGradient(listOf(Color.DarkGray, Color.DarkGray)), shape = RoundedCornerShape(20.dp   ), width = 1.dp )) {
                           IconButton(onClick = { /*TODO*/ }) {
                               Icon( Icons.Outlined.KeyboardArrowUp, contentDescription = "down", tint = Color.White )
                           }
                           Text(text = "11", color = Color.White)
                           IconButton(onClick = { /*TODO*/ }) {
                               Icon( Icons.Outlined.KeyboardArrowDown, contentDescription = "down" , tint = Color.White)
                           }
                       }
                       Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier
                           .border(
                               brush = Brush.horizontalGradient(
                                   listOf(
                                       Color.DarkGray,
                                       Color.DarkGray
                                   )
                               ), shape = RoundedCornerShape(10.dp), width = 1.dp
                           )
                           .padding(8.dp)) {
                           Icon(painterResource(id = R.drawable.outline_textsms_24), contentDescription = "text sms", tint = Color.White
                           )
                           Text(text = "17 comments", color = Color.White, fontWeight = FontWeight.SemiBold)
                       }
                   }
                    Row(modifier = Modifier
                        .border(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color.DarkGray,
                                    Color.DarkGray
                                )
                            ), shape = RoundedCornerShape(10.dp), width = 1.dp
                        )
                        .padding(6.dp)) {
                        Icon(Icons.Default.Share, contentDescription = "text sms", tint = Color.White
                        )
                        Text(text = "5", color = Color.White, fontWeight = FontWeight.SemiBold)
                    }
                }
                Divider(color = Color.DarkGray)


            }
        }
        item {

        }
    }
}
}