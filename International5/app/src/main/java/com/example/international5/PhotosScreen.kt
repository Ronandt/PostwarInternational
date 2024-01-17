package com.example.international5

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosScreen(navController: NavController) {
    var dialog by remember {mutableStateOf(false)}
    var currentPage by remember {mutableStateOf(0)}
    val context = LocalContext.current
    val photos = remember {
        JsonRetriever(context).photos()
    }

    BoxWithConstraints {
        if(maxWidth > maxHeight) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(26.dp), verticalArrangement = Arrangement.spacedBy(26.dp), modifier = Modifier.padding(20.dp)) {
                    var first = currentPage * 6
                    var second = (currentPage + 1) * 6
                    items(6) {
                        var dropdown by remember {mutableStateOf(false)}

                        if(photos?.getOrNull((currentPage * 6) + it)?.votes != null) {

                        Box(modifier = Modifier
                            .size(280.dp)
                            .combinedClickable(
                                onClick = { dialog = true },
                                onLongClick = {
                                    dropdown = true
                                }
                            )
                           ) {
                            DropdownMenu(expanded = dropdown, onDismissRequest = { dropdown = false }) {
                                DropdownMenuItem(text = { Text("Share")}, onClick = {

                                })
                            }
                            Image(painterResource(R.drawable.ic_launcher_background), modifier = Modifier.matchParentSize(), contentDescription = "", contentScale = ContentScale.Crop)
                            Column(modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(Color(0xAA000000))) {
                                Text(text = "Votes" + photos?.get((currentPage * 6) + it)?.votes, color=  Color.White)
                                Text(text = "Popularity" + photos?.get((currentPage * 6) + it)?.popularity, color = Color.White)
                            }
                        }
                        if(dialog) {
                            var scale by remember {mutableStateOf(1.0f)}
                            var transformState = rememberTransformableState {
                                    zoomChange, offsetChange, rotationChange ->
                                scale *= zoomChange

                            }
                            Dialog(onDismissRequest = { dialog = false }) {
                                Column {
                                    Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Stuff", modifier = Modifier
                                        .graphicsLayer(scaleX = scale, scaleY = scale)
                                        .transformable(state = transformState)
                                        .size(500.dp))

                                }

                            }
                        }
                    }}
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
                    repeat(Math.ceil((photos?.size?.div(6.0)!!)).toInt()) {

                        Text(text = (it + 1).toString(), fontSize = 24.sp, modifier = Modifier
                            .clickable {
                                currentPage = it
                            }
                            .padding(10.dp), fontWeight = if(it == currentPage) FontWeight.Bold else FontWeight.Normal)
                    }
                }
            }
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyHorizontalGrid(rows = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(26.dp), verticalArrangement = Arrangement.spacedBy(26.dp), modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .padding(20.dp)) {
                    items(6) {
                        if(photos?.getOrNull((currentPage * 6) + it)?.votes != null) {
                            Box(modifier = Modifier
                                .size(320.dp)
                                .clickable {
                                    dialog = true
                                }) {

                                Image(painterResource(R.drawable.ic_launcher_background), modifier = Modifier
                                    .matchParentSize()
                                    .clickable {
                                        dialog = true
                                    }, contentDescription = "", contentScale = ContentScale.Crop)
                                Column(modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .background(Color(0xAA000000))) {
                                    Text(text = "Votes" + photos?.get((currentPage * 6) + it)?.votes, color=  Color.White)
                                    Text(text = "Popularity" + photos?.get((currentPage * 6) + it)?.popularity, color = Color.White)
                                }
                            }
                            if(dialog) {
                                var scale by remember {mutableStateOf(1.0f)}
                                var transformState = rememberTransformableState {
                                        zoomChange, offsetChange, rotationChange ->
                                    scale *= zoomChange

                                }
                                Dialog(onDismissRequest = { dialog = false }) {
                                    Column {
                                        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Stuff", modifier = Modifier
                                            .graphicsLayer(scaleX = scale, scaleY = scale)
                                            .transformable(state = transformState)
                                            .size(500.dp))

                                    }

                                }
                            }
                        }

                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
                    repeat(Math.ceil((photos?.size?.div(6.0)!!)).toInt()) {

                        Text(text = (it + 1).toString(), fontSize = 24.sp, fontWeight = if(it == currentPage) FontWeight.Bold else FontWeight.Normal,modifier = Modifier
                            .clickable {
                                currentPage = it
                            }
                            .padding(10.dp))
                    }
                }
            }
        }

    }


}