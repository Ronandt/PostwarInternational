package com.example.worldskills_am_b

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.layout.Spacer
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    var pagerState = rememberPagerState(0) {
        3
    }
    LaunchedEffect(Unit) {
        while(true) {
            delay(1000)
            pagerState.animateScrollToPage((pagerState.currentPage + 1) %3)
        }
    }
    var navigationList = listOf("skills", "photos", "videos")
    var imageList = listOf(R.drawable.ws01, R.drawable.ws02, R.drawable.ws03)

    Column(modifier = Modifier.fillMaxSize()) {
        Box {
            HorizontalPager(state = pagerState) {

                Image(painter = painterResource(imageList[it]), contentDescription =  "This is the image ", modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp), contentScale = ContentScale.Crop)


            }
            Row(horizontalArrangement = Arrangement.spacedBy(2.dp   ), modifier =Modifier.align(Alignment.BottomCenter)) {
                repeat(3) {

                    if(pagerState.currentPage != it) {
                        androidx.compose.foundation.layout.Spacer(modifier = Modifier

                            .size(20.dp)
                            .background(color = Color.LightGray, shape = CircleShape))
                    } else {
                        androidx.compose.foundation.layout.Spacer(modifier = Modifier

                            .size(20.dp)
                            .background(color = Color.DarkGray, shape = CircleShape))
                    }

                }
            }

        }
androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(20.dp ))
Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
    navigationList.forEach {
        Card(modifier = Modifier
            .height(150.dp)
            .width(300.dp), onClick = {navController.navigate(it)}) {
            Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxSize()) {
                Text(text = it.capitalize(), fontWeight = FontWeight.SemiBold, fontSize = 26.sp )
            }

        }
    }

}
    }

}