package com.example.international

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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

@Composable
fun AboutScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Header(text = "About")
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.height(750.dp)) {
            Column(modifier = Modifier.fillMaxHeight()) {
                Box() {    Image(painter = painterResource(id = R.drawable.ws2), contentDescription = "Inspire", modifier = Modifier
                    .width(650.dp)
                    .height(400.dp), contentScale = ContentScale.Crop)
                    Spacer(modifier = Modifier
                        .matchParentSize()
                        .background(Color(0x997191EE)))
                    Text(text = "Inspire", fontWeight = FontWeight.Black, color= Color.White, fontSize = 30.sp, modifier = Modifier.align(
                        Alignment.Center))
                }

                Box {
                    Image(painter = painterResource(id = R.drawable.ws3), contentDescription = "Inspire", modifier = Modifier
                        .width(650.dp)
                        .height(400.dp), contentScale = ContentScale.Crop)
                    Spacer(modifier = Modifier
                        .matchParentSize()
                        .background(Color(0x990A2061)))
                    Text(text = "Develop", fontWeight = FontWeight.Black, color= Color.White, fontSize = 30.sp, modifier = Modifier.align(
                        Alignment.Center))
                }


            }
            Box {
                Image(painterResource(R.drawable.ws), contentDescription = "Influence", modifier = Modifier
                    .fillMaxHeight()
                    .width(350.dp), contentScale = ContentScale.Crop)
                Spacer(modifier = Modifier
                    .matchParentSize()
                    .background(Color(0x99FA2020)))
                Text(text = "Influence", fontWeight = FontWeight.Black, color= Color.White, fontSize = 30.sp, modifier = Modifier.align(
                    Alignment.Center))
            }


        }
    }
}