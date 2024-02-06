package com.example.international_module_c_am

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international_module_c_am.ui.theme.CardGray
import com.example.international_module_c_am.ui.theme.DarkBlue
import com.example.international_module_c_am.ui.theme.DarkGray
import com.example.international_module_c_am.ui.theme.LightBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    var settings by remember {mutableStateOf(false)}
    LaunchedEffect(Unit) {
        settings = sharedPref.getBoolean("darkMode", false)
    }
    val scope = rememberCoroutineScope()
    var backgroundSize by remember {mutableStateOf(360.dp)}
    val navList = remember {listOf("News","Results", "History")}
    var pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        3
    }

    val  background by animateColorAsState(targetValue = if(settings) DarkGray else White)
    val text1ColorAsState by animateColorAsState(targetValue = if(settings) Color.White else DarkBlue)
    val text2ColorAsState by animateColorAsState(targetValue = if(settings) Color.LightGray else Color.DarkGray)
    val cardBackgroundAsState by animateColorAsState(targetValue =if (settings) CardGray else Color.White )
    var logoSize by remember {mutableStateOf(280.dp)}
    var openSheet by remember {mutableStateOf(false)}
    var sheetState = rememberModalBottomSheetState()
    LaunchedEffect(Unit) {
        sheetState.hide()
    }
    Column(modifier = Modifier.background(background)) {
        Column(modifier = Modifier
            .animateContentSize()
            .fillMaxWidth()
            .height(backgroundSize)

            .background(
                Brush.verticalGradient(listOf(LightBlue, DarkBlue)),
                shape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp)

            )) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                IconButton(onClick = {
                    scope.launch {
                        sheetState.expand()
                        openSheet = true
                    }

                                     }, modifier = Modifier.align(Alignment.End)) {
                    Icon(Icons.Default.Settings, tint = Color.White, contentDescription = "Settings")
                }
                Image(painter = painterResource(R.drawable.worldskills), contentDescription = "Worldskills logo", modifier = Modifier
                    .animateContentSize()
                    .fillMaxWidth()
                    .height(logoSize), contentScale = ContentScale.Fit    )
                Box {
                    RippleLoadingAnimation()
                    TabRow(selectedTabIndex = pagerState.currentPage,backgroundColor = Color(0x00000000), divider = {

                    }, indicator = {

                        Divider(color = Color.White,
                            modifier = Modifier
                                .requiredWidth(50.dp)
                                .tabIndicatorOffset(it[pagerState.currentPage])
                                .offset(x = -125.dp)
                        )
                    }) {
                        repeat(3) {
                            Tab(selected = it == pagerState.currentPage , onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(it)
                                }
                                println("?????? $it")
                            }) {

                                Text(text = navList[it], fontWeight = FontWeight.SemiBold, color =  if(it == pagerState.currentPage) Color.White else Color(0x88FFFFFF) )
                                Spacer(modifier = Modifier.height(14.dp))
                            }
                        }
                    }
                }

            }








        }
        Spacer(modifier = Modifier.height(20.dp))
        val internetObserver = InternetObserver(context).observe().collectAsState(initial = false)
        HorizontalPager(state = pagerState) { it ->
            if(!internetObserver.value  ) {
                NoInternetScreen()
            } else {
                when(it) {

                    0 -> {
                        NewsScreen(navController = navController) { b->
                            if (b) {
                                logoSize = 100.dp
                                backgroundSize = 180.dp
                            } else {
                                logoSize = 280.dp
                                backgroundSize = 360.dp
                            }

                        }
                    }
                    1 -> {
                        ResultsScreen(navController = navController){ b: Boolean->
                            if (b) {
                                logoSize = 100.dp
                                backgroundSize = 180.dp
                            } else {
                                logoSize = 280.dp
                                backgroundSize = 360.dp
                            }

                        }
                    }
                    2 -> {
                        HistoryScreen(navController = navController){ b: Boolean->
                            if (b) {
                                logoSize = 100.dp
                                backgroundSize = 180.dp
                            } else {
                                logoSize = 280.dp
                                backgroundSize = 360.dp
                            }

                        }
                    }
                }
            }

        }
        if(openSheet) {
            ModalBottomSheet(onDismissRequest = {
                scope.launch {
                    sheetState.hide()

                }
                openSheet = false
            }, sheetState = sheetState, containerColor = background) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = "Settings", fontWeight = FontWeight.Bold, color = text1ColorAsState, fontSize = 22.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider()
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Dark Mode", fontWeight = FontWeight.SemiBold, fontSize = 18.sp, color = text2ColorAsState)
                        androidx.compose.material3.Switch(checked = settings, onCheckedChange = {
                       settings = !settings
                            with(sharedPref.edit()) {
                                putBoolean("darkMode", settings)
                                apply()
                            }
                        }, colors = SwitchDefaults.colors(checkedTrackColor = Color.Green))
                    }
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }

    }



}


@Composable
fun RippleLoadingAnimation(
    circleColor: Color = LightBlue,
    animationDelay: Int = 3000
) {

// 3 circles
    val circles = listOf(
        remember {
            androidx.compose.animation.core.Animatable(initialValue = 0f)
        },
        remember {
            androidx.compose.animation.core.Animatable(initialValue = 0f)
        },
        remember {
            androidx.compose.animation.core.Animatable(initialValue = 0f)
        }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(Unit) {
            // Use coroutine delay to sync animations
            // divide the animation delay by number of circles
            delay(timeMillis = (animationDelay / 3L) * (index + 1))

            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDelay,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

// outer circle
    Box(
        modifier = Modifier
            .offset(y = 10.dp)
            .size(size = 1200.dp)
            .background(color = Color.Transparent)
    ) {
        // animating circles
        circles.forEachIndexed { index, animatable ->
            Box(
                modifier = Modifier
                    .scale(scale = animatable.value)
                    .size(size = 1200.dp)
                    .clip(shape = CircleShape)
                    .background(
                        color = circleColor
                            .copy(alpha = (1 - animatable.value))
                    )
            ) {
            }
        }
    }
}