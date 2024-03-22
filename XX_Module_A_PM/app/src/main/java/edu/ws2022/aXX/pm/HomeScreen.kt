package edu.ws2022.aXX.pm

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var listOfPictures = listOf(
        R.drawable.worldskills2,
        R.drawable.worldskills3,
        R.drawable.worldskills4
    )
    var pagerState = rememberPagerState(0) {
        3
    }
    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(1000)
            pagerState.animateScrollToPage((pagerState.currentPage +1) % 3)
        }

    }
    var navigationList = listOf("skills", "photos", "videos")
    Column(modifier = Modifier.fillMaxWidth()) {
        Box() {
            HorizontalPager(state = pagerState) {
                Image(painter = painterResource(id = listOfPictures[it]), contentDescription = "Image" , modifier = Modifier
                    .fillMaxWidth()
                    .height(550.dp), contentScale = ContentScale.Crop)


            }
            Row(modifier = Modifier.align(Alignment.BottomCenter), horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterHorizontally)) {
                repeat(3) {
                    Spacer(modifier = Modifier
                        .size(14.dp)
                        .background(if (it == pagerState.currentPage) Color.DarkGray else Color.LightGray, shape = CircleShape))

                }
            }


        }

        Row(modifier = Modifier.padding(20.dp   ).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)) {
            navigationList.forEach {
                Card(onClick = {navController.navigate(it   )}, modifier = Modifier.height(200.dp   ).width(250.dp  )) {
                    Text(text = it.capitalize(), modifier = Modifier, fontWeight = FontWeight.Bold, fontSize = 30.sp    )

                }
            }

        }
    }



}