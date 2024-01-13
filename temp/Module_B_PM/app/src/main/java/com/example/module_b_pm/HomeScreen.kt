package com.example.module_b_pm

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.time.delay
import java.security.AccessControlException

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController:NavController) {

    var navigationList = remember {listOf("skills", "photos", "videos")}
    var imageList = remember{ listOf(R.drawable.one, R.drawable.two, R.drawable.three)}
    var pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
    imageList.size
    }
    LaunchedEffect(Unit) {
        while(true) {
            kotlinx.coroutines.delay(3000L)
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % 3)

        }

    }
    Column {
        Box {

            HorizontalPager(state = pagerState) {
                Image(painter = painterResource(id = imageList[it]), contentDescription = "Image", modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp), contentScale = ContentScale.Crop  )
            }

            Row(modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomCenter), horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
                repeat(3) {
                    if(pagerState.currentPage == it) {
                        Spacer(modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.White))
                    } else {
                        Spacer(modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray))
                    }

                }
            }
        }
        Row(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)) {

                navigationList.forEach {
                    Card(modifier = Modifier.size(250.dp, 160.dp), colors = CardDefaults.cardColors(containerColor = Color.Black)) {
                        Text(text = it.capitalize(), fontSize = 30.sp, color = Color.White)
                    }
                }

        }
    }
   
  
    
}