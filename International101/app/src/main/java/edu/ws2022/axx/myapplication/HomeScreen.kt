package edu.ws2022.axx.myapplication

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import java.security.KeyStore.TrustedCertificateEntry

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val listOfImages = remember {listOf(R.drawable.one, R.drawable.two, R.drawable.three)}
    var navigationList = remember {listOf("skills", "photos", "videos")}
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            pagerState.animateScrollToPage((pagerState.currentPage + 1) %3)
        }

    }
    Column() {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(550.dp)) {
            HorizontalPager(pageCount = listOfImages.size, state = pagerState) {

                Image(
                    painter = painterResource(id = listOfImages[it]),
                    contentDescription = "Images", modifier = Modifier
                        .fillMaxWidth().fillMaxHeight()
                       , contentScale = ContentScale.Crop
                )


            }
            Row(modifier = Modifier.align(Alignment.BottomCenter), horizontalArrangement = Arrangement.spacedBy(6.dp),) {
                repeat(listOfImages.size)  {
                    Spacer(modifier = Modifier
                        .size(8.dp)
                        .background(
                            if (pagerState.currentPage == it) Color.DarkGray else Color.LightGray,
                            shape = CircleShape
                        ))
                }

            }

        }
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp), horizontalArrangement = Arrangement.spacedBy(18.dp   , Alignment.CenterHorizontally)) {
            navigationList.forEach {
                Box(modifier = Modifier.background(Color.Black).width(270.dp    ).height(150.dp ).clickable {
                                                                                                            navController.navigate("$it")
                }, contentAlignment = Alignment.Center, ) {
                    Text(text = it.capitalize(), color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 34.sp     )
                }

            }

        }
    }
}