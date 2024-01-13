package com.example.international

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international.ui.theme.HotPink
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SkillsScreen(navController: NavController) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
       8
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Header(text = "Skills")
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(100.dp))
            HorizontalPager(state = pagerState, beyondBoundsPageCount = 1, modifier= Modifier.fillMaxWidth()) {
                when(it) {
                    0 -> {
                        Box(modifier = Modifier.size(800.dp, 620.dp)){
                            Image(painter = painterResource(id = R.drawable.cloudcomputing), contentDescription =  "Cloud computing", contentScale = ContentScale.Crop, modifier = Modifier.matchParentSize())
                            Column(modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(HotPink)
                                .padding(vertical = 40.dp, horizontal = 20.dp)) {
                                Text(text = "Cloud Computing", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 30.sp)
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(text = "Cloud comptuing has become a very key part of corporate digital transofrmation strategy as companies migrate their hpysical IT activites, such as the storage and on-site serversi nto virtual environment", color = Color.White, modifier = Modifier.width(300.dp))
                            }
                        }

                    }
                    else -> {
                        Box(modifier = Modifier.size(800.dp, 620.dp)){
                            Image(painter = painterResource(id = R.drawable.cloudcomputing), contentDescription =  "Cloud computing", contentScale = ContentScale.Crop, modifier = Modifier.matchParentSize())
                            Column(modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(HotPink)
                                .padding(vertical = 40.dp, horizontal = 20.dp)) {
                                Text(text = "Cloud Computing", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 30.sp)
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(text = "Cloud comptuing has become a very key part of corporate digital transofrmation strategy as companies migrate their hpysical IT activites, such as the storage and on-site serversi nto virtual environment", color = Color.White, modifier = Modifier.width(300.dp))
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
            OutlinedIconButton(modifier = Modifier.border(color = HotPink, shape = CircleShape, width = 5.dp),enabled = pagerState.currentPage > 0,onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }

            }, colors = IconButtonDefaults.outlinedIconButtonColors(contentColor = HotPink)) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "left", modifier = Modifier.size(20.dp))
            }
            repeat(8) {
                if(pagerState.currentPage == it) {
                    Spacer(modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(HotPink))
                        
                } else {
                    Spacer(modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                       )
                }

            }
            OutlinedIconButton(modifier = Modifier.border(color = HotPink, shape = CircleShape, width = 5.dp),enabled = pagerState.currentPage < 7,onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }, colors = IconButtonDefaults.outlinedIconButtonColors(contentColor = HotPink)) {
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "right", modifier = Modifier.size(20.dp))
            }
        }

    }
}