package com.example.international

import android.graphics.drawable.Icon
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    var switch by remember {mutableStateOf(false)}
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        3
    }
    var settings by remember {mutableStateOf(false)}
    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xFF335F82), Color(0xFF053B69)
                        )
                    ), shape = RoundedCornerShape(0, 0, 10, 10)
                )) {
                Column(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        IconButton(onClick = { settings = true }) {
                            androidx.compose.material3.Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)

                        }
                    }
                    Image(painter = painterResource(R.drawable.worldskills), contentDescription = "Worldskills Logo", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxWidth())
                    TabRow(selectedTabIndex = pagerState.currentPage, modifier = Modifier.requiredHeight(59.dp), containerColor = Color.Transparent, divider = {
                        Divider( color = Color.Transparent, thickness = 3.dp)}, indicator = {

                            tabPositions -> Divider(
                        Modifier
                            .requiredWidth(60.dp)
                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .offset(x = -120.dp), color = Color.White, thickness = 4.dp)
                    }
                    ) {
                        (0..2).forEach {

                            Tab(selected = pagerState.currentPage == it , onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(it)
                                }
                            }, selectedContentColor = Color.White) {

                                var nameList = remember {listOf("News", "Results", "History")}

                                Text(text = nameList[it], fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }



                    }

                }

            }

            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) {
                when(it) {
                    0 -> NewsScreen(navController = navController)
                    1 -> ResultScreen(navController = navController)
                    2 -> HistoryScreen(navController = navController)

                }
            }


        }
        AnimatedVisibility(visible = settings) {

                Spacer(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xAA000000))
                    .clickable {
                        settings = false
                    })

            Column(modifier = Modifier
                .fillMaxSize()

             ) {
                Spacer(modifier = Modifier.weight(1f))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Settings", fontWeight = FontWeight.Bold, color = Color(0xFF335F82))
                    Divider()
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Dark  mode", fontWeight = FontWeight.Bold)

                        Switch(checked = switch , onCheckedChange = {switch = !switch}, colors = SwitchDefaults.colors(
                            checkedTrackColor = Color.Green
                        ))
                    }
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }



        }



    }
}