package com.example.international.presentation

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import com.example.international.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    var pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
       3
    }
    LaunchedEffect(key1 = Unit) {
        while(true) {
            delay(3000)

            println("Before:" +pagerState.currentPage)
            pagerState.animateScrollToPage(if((pagerState.currentPage+ 1) == 3) 0 else pagerState.currentPage+ 1)
            println("After:" +pagerState.currentPage)
        }
    }

    
    val cardList = remember { listOf("skills", "photos", "videos")}
    val imageList = remember {listOf(R.drawable.a, R.drawable.img_8524_48602418412_o, R.drawable.b)}
    Column(modifier = Modifier.fillMaxSize()) {
        Column() {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .offset(y = -70.dp)
                .background(Color.Black)
                ) {
                HorizontalPager(state = pagerState, userScrollEnabled = false) {
                    Image(painterResource(imageList[it]), contentDescription = "Image", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                }


                    Text(text = "Banner")

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter), horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally), ) {
                        repeat(3) {

                            Spacer(modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(if (pagerState.currentPage == it) Color.White else Color.Gray)
                                )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }


            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)) {
                cardList.forEach { it ->
                    Card(onClick = {navController.navigate(it)}, modifier = Modifier.size(300.dp, 150.dp)) {
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                            Text(text = it.uppercase(), fontSize = 30.sp, fontWeight = FontWeight.Bold)
                        }


                    }
                }
            }


        }

    }
}