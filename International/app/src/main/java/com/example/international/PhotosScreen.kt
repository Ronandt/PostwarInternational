package com.example.international

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.input.key.Key.Companion.Ro
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.international.ui.theme.HotPink

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotosScreen(navController: NavController) {
    var dialog by remember {mutableStateOf("")}
    var swipe = rememberSwipeableState(0)
    var swipe2 = rememberSwipeableState(0)
    LaunchedEffect(swipe.currentValue, swipe.offset, swipe) {
        println(swipe.offset)
        if(swipe.currentValue == 1) {
            navController.navigate("skills") {
                launchSingleTop = true
            }
        }
        if(swipe.currentValue == -1) {
            navController.navigate("statistic") {
                launchSingleTop = true
            }
        }
    }


    Spacer(modifier = Modifier.height(10.dp))
Row(modifier = Modifier
    .fillMaxWidth()
    .padding(end = 20.dp, top = 20.dp) .swipeable(
        state = swipe,
        anchors = mapOf(-10000f to -1, 0f to 0, 10000f to 1),
        reverseDirection = true,
        orientation = Orientation.Vertical
    )
    .offset {
        IntOffset(0, -swipe.offset.value.toInt())
    }) {
    val listThing = remember {listOf("https://d3izhzxqbknro4.cloudfront.net/web_development.jpg","https://d3izhzxqbknro4.cloudfront.net/photo-6.jpg","https://d3izhzxqbknro4.cloudfront.net/photo-2.jpg","https://d3izhzxqbknro4.cloudfront.net/photo-7.jpg")}
    Column(modifier = Modifier.padding(end = 10.dp)) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.width(480.dp)) {
            items(4) {
                AsyncImage(listThing[it], contentDescription = "", modifier = Modifier
                    .size(230.dp)
                    .clickable {
                        dialog =listThing[it]
                    }, contentScale = ContentScale.Crop, )
            }


        }
        Spacer(modifier = Modifier.height(10.dp))
        AsyncImage("https://d3izhzxqbknro4.cloudfront.net/network_administration.jpg", contentDescription = "", modifier = Modifier
            .width(480.dp)
            .height(230.dp)
            .clickable {
                dialog = "https://d3izhzxqbknro4.cloudfront.net/network_administration.jpg"
            }, contentScale = ContentScale.Crop)
    }
    Column() {
        AsyncImage("https://d3izhzxqbknro4.cloudfront.net/music_composition.jpg", contentDescription = "", modifier = Modifier
            .size(474.dp)
            .clickable {
                dialog = "https://d3izhzxqbknro4.cloudfront.net/music_composition.jpg"
            }, contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            AsyncImage("https://d3izhzxqbknro4.cloudfront.net/film_production.jpg", contentDescription ="", contentScale = ContentScale.Crop    , modifier = Modifier
                .size(230.dp)
                .clickable {
                    dialog = "https://d3izhzxqbknro4.cloudfront.net/film_production.jpg"
                })
            AsyncImage("https://d3izhzxqbknro4.cloudfront.net/nursing.jpg", contentDescription ="" , contentScale = ContentScale.Crop , modifier = Modifier
                .size(230.dp)
                .clickable {
                    dialog ="https://d3izhzxqbknro4.cloudfront.net/nursing.jpg"
                })
        }
    }

}

        if(dialog != "") {
            Dialog(onDismissRequest = { dialog = ""}) {
                Box( contentAlignment = Alignment.Center) {

                    if(dialog != "") {
                        AsyncImage( dialog, contentDescription ="Image", contentScale = ContentScale.Crop)
                        IconButton(onClick = { dialog = ""}, Modifier.align(Alignment.TopEnd).padding(10.dp).background(Color.White, CircleShape)) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = HotPink)
                        }
                    }

                }
            }
        }



}