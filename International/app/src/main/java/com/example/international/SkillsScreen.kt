package com.example.international

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.international.ui.theme.HotPink
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun SkillsScreen(navController: NavController) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var skills = remember {mutableStateListOf<SkillsItem>()}
    LaunchedEffect(Unit){
        WorldSkillsAPI().getSkills()?.let { skills.addAll(it) }
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        skills?.size!!
    }
    var swipe = rememberSwipeableState(0)
    LaunchedEffect(swipe.currentValue, swipe.offset, swipe) {
        println(swipe.offset)
        if(swipe.currentValue == 1) {
            navController.navigate("photos") {
                launchSingleTop = true
            }
        }

    }
Column(modifier = Modifier.fillMaxWidth() .padding(end = 20.dp, top = 20.dp) .swipeable(
    state = swipe,
    anchors = mapOf(0f to 0, 10000f to 1),
    reverseDirection = false,
    orientation = Orientation.Vertical
)
    .offset {
        IntOffset(0, swipe.offset.value.toInt())
    }) {
    HorizontalPager(pageSpacing = -160.dp,state = pagerState, beyondBoundsPageCount = 2, modifier = Modifier
        .height(600.dp)
        .fillMaxWidth()
        .offset(x = 100.dp)) {
        Box(modifier = Modifier
            .size(800.dp, 600.dp)
            .graphicsLayer {
                // Calculate the absolute offset for the current page from the
                // scroll position. We use the absolute value which allows us to mirror
                // any effects for both directions
                val pageOffset = (
                        (pagerState.currentPage - it) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue


            }) {
            val animateFloatAsStates by animateFloatAsState(targetValue = if (it == pagerState.currentPage) 1f else 0.5f)
            AsyncImage(skills?.get(it)?.image, contentDescription = null, modifier = Modifier
                .matchParentSize()
                .alpha(animateFloatAsStates), contentScale = ContentScale.Crop)
            Column(modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(HotPink)
                .padding(vertical = 30.dp, horizontal = 15.dp)) {
                Box() {
                    Image(painter = painterResource(id = R.drawable.decoration_image), contentDescription = "Decoration", modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 10.dp, y = -28.dp)
                        .size(80.dp), contentScale = ContentScale.Crop)
                    Column {
                        skills?.get(it)?.let { it1 -> Text(text = it1.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp) }
                        Spacer(modifier = Modifier.height(14.dp))
                        skills?.get(it)?.let { it1 -> Text(text = it1.description, color = Color.White, modifier = Modifier.width(350.dp)) }

                    }
                }

            }
        }



    }
    Spacer(modifier = Modifier.height(20.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterHorizontally), verticalAlignment = Alignment.CenterVertically) {
        IconButton(enabled = pagerState.currentPage != 0, onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage -1)
            }
         }, modifier = Modifier.border(shape = CircleShape, width = 3.dp, color = if(pagerState.currentPage != 0) HotPink else Color.LightGray)) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Left", tint =if(pagerState.currentPage != 0) HotPink else Color.LightGray, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(1.dp))
        repeat(skills.size) {


            Spacer(modifier = Modifier
                .size(18.dp)
                .clip(CircleShape)
                .background(if (pagerState.currentPage == it) HotPink else Color.LightGray))
        }
        Spacer(modifier = Modifier.width(1.dp))

        IconButton(enabled = pagerState.currentPage < skills?.size!! -1,onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage +1)
            }

        }, modifier = Modifier.border(shape = CircleShape, width = 3.dp, color =  if(pagerState.currentPage < skills?.size!! -1) HotPink  else Color.LightGray )) {
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Left", tint = if(pagerState.currentPage < skills?.size!! -1) HotPink  else Color.LightGray   , modifier = Modifier.size(20.dp))
        }
    }
}
}