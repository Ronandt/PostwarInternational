package com.example.international

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AboutScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
   // var about = remember {JsonRetriever(context).about()}
    var about = remember { mutableStateListOf<AboutItem>()}
    LaunchedEffect(Unit) {
        WorldSkillsAPI().getAbout()?.let { about.addAll(it) }
    }

    var activated by remember {mutableStateOf(-1)}
    var composableBounds by remember {mutableStateOf<Rect?>(null)}
    var swipe = rememberSwipeableState(0)
    LaunchedEffect(swipe.currentValue, swipe.offset, swipe) {
        println(swipe.offset)
        if(swipe.currentValue == 1) {
            navController.navigate("statistic")
        }
    }
    Column(modifier = Modifier.fillMaxWidth()
            .swipeable(
            state = swipe,
        anchors = mapOf(0f to 0, 10000f to 1),
        reverseDirection = true,
        orientation = Orientation.Vertical
    )
        .offset {
            IntOffset(0, -swipe.offset.value.toInt())
        }) {
        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(800.dp)
            .onGloballyPositioned {
                composableBounds = it.boundsInWindow()
            }) {

            Column(
             ) {
                if(activated == -1 || activated == 0) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier
                        .then(
                            if (activated == 0) Modifier
                                .fillMaxWidth()
                                .height(640.dp) else Modifier.size(700.dp, 320.dp)
                        )
                        .zIndex(if (activated == 0) 1000f else 1f)
                        .clickable {
                            activated = if (activated == -1) 0 else -1
                        }) {
                        Image(painter = painterResource(R.drawable.ws01), contentDescription ="First image", contentScale = ContentScale.Crop, modifier = Modifier
                            .animateContentSize()
                            .matchParentSize())
                        Spacer(modifier = Modifier
                            .animateContentSize()
                            .matchParentSize()
                            .background(Color(0xAa255F86)))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            about?.getOrNull(0)?.title?.let {
                                Text(
                                    text = it,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White,
                                    fontSize = 28.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            AnimatedVisibility(activated == 0) {
                                about?.getOrNull(0)?.description?.let { Text(text = it, color = Color.White, modifier = Modifier.width(340.dp), textAlign =  TextAlign.Center) }
                            }
                        }



                    }
                }


                if(activated == -1||activated == 1) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier
                        .then(
                            if (activated == 1) Modifier
                                .fillMaxWidth()
                                .height(640.dp) else Modifier.size(700.dp, 320.dp)
                        )
                        .zIndex(if (activated == 1) 1000f else 1f)
                        .clickable {
                            activated = if (activated == -1) 1 else -1

                        }) {
                        Image(painter = painterResource(R.drawable.ws02), contentDescription ="First image", contentScale = ContentScale.Crop, modifier = Modifier
                            .animateContentSize()
                            .matchParentSize())
                        Spacer(modifier = Modifier
                            .animateContentSize()
                            .matchParentSize()
                            .background(Color(0xAA422586)))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            about?.getOrNull(1)?.title?.let {
                                Text(
                                    text = it,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White,
                                    fontSize = 28.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            AnimatedVisibility(activated == 1) {
                                about?.getOrNull(1)?.description?.let { Text(text = it, color = Color.White, modifier = Modifier.width(340.dp), textAlign =  TextAlign.Center) }
                            }
                        }


                    }

                }
                }

            Column() {
                if(activated == -1||activated == 2) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier
                        .then(
                            if (activated == 2) Modifier
                                .fillMaxWidth()
                                .height(640.dp) else Modifier
                                .fillMaxHeight(0.888f)
                                .width(width = 320.dp)
                        )
                        .zIndex(if (activated == 2) 1000f else 1f)
                        .clickable {
                            activated = if (activated == -1) 2 else -1

                        }) {
                        Image(painter = painterResource(R.drawable.ws03), contentDescription ="First image", contentScale = ContentScale.Crop, modifier = Modifier
                            .animateContentSize()
                            .matchParentSize())
                        Spacer(modifier = Modifier
                            .animateContentSize()
                            .matchParentSize()
                            .background(Color(0xAA862525)))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            about?.getOrNull(2)?.title?.let {
                                Text(
                                    text = it,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White,
                                    fontSize = 28.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            AnimatedVisibility(activated == 2) {
                                about?.getOrNull(2)?.description?.let { Text(text = it, color = Color.White, modifier = Modifier.width(340.dp), textAlign =  TextAlign.Center) }
                            }
                        }


                    }

                }


            }
        }
    }
}