package com.example.international

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AboutScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
   // var about = remember {JsonRetriever(context).about()}
    var about = remember { mutableStateListOf<AboutItem>()}
    LaunchedEffect(Unit) {
        WorldSkillsAPI().getAbout()?.let { about.addAll(it) }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(1000.dp)) {

            Column {

                Box(contentAlignment = Alignment.Center, modifier = Modifier.size(700.dp,320.dp).clickable{}) {
                    Image(painter = painterResource(R.drawable.ws01), contentDescription ="First image", contentScale = ContentScale.Crop, modifier = Modifier.matchParentSize())
                    Spacer(modifier = Modifier
                        .matchParentSize()
                        .background(Color(0xAa255F86)))
                    about?.getOrNull(0)?.title?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            fontSize = 28.sp
                        )
                    }

                }

                Box(contentAlignment = Alignment.Center, modifier = Modifier.size(700.dp,320.dp).clickable{}) {
                    Image(painter = painterResource(R.drawable.ws02), contentDescription ="First image", contentScale = ContentScale.Crop, modifier = Modifier.matchParentSize())
                    Spacer(modifier = Modifier
                        .matchParentSize()
                        .background(Color(0xAA422586)))
                    about?.getOrNull(1)?.title?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            fontSize = 28.sp
                        )
                    }

                }

            }
            Column() {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight(0.888f).width(width = 320.dp).clickable{}) {
                    Image(painter = painterResource(R.drawable.ws03), contentDescription ="First image", contentScale = ContentScale.Crop, modifier = Modifier.matchParentSize())
                    Spacer(modifier = Modifier
                        .matchParentSize()
                        .background(Color(0xAA862525)))
                    about?.getOrNull(2)?.title?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            fontSize = 28.sp
                        )
                    }

                }
            }
        }
    }
}