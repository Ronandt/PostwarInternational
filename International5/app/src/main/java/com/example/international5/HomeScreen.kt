package com.example.international5

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var cardList = listOf("skills", "photos", "videos")
    var imageList = listOf(R.drawable.ws1, R.drawable.ws2, R.drawable.ws3)
    var pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        3
    }

    LaunchedEffect(Unit) {
        while(true) {

            delay(2000)
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % 3)

        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Box {
            HorizontalPager(state = pagerState, userScrollEnabled = false) {
                Image(painterResource(imageList[it]), contentDescription = "Image", modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp), contentScale = ContentScale.Crop)




            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),horizontalArrangement = Arrangement.spacedBy(6.dp, alignment = Alignment.CenterHorizontally),){
                repeat(3) {

                    Spacer(modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .background(  if(pagerState.currentPage == it) Color.DarkGray else Color.LightGray))
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterHorizontally)) {

            cardList.forEach {
                Card(modifier = Modifier.height(200.dp).width(300.dp), onClick = {navController.navigate(it)}) {
                    Text(text = it.capitalize(), fontSize = 40.sp, fontWeight = FontWeight.Bold)
                }
            }

        }
    }

}