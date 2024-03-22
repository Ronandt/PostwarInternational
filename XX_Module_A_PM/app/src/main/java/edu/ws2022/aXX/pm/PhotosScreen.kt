package edu.ws2022.aXX.pm

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosScreen(navController: NavController) {
    var view = LocalView.current
    val context = LocalContext.current
    var horizontal = view.width > view.height
    var photos = remember {mutableStateListOf<Photo>()}
    var pagination by remember {mutableStateOf(0)}
    LaunchedEffect(Unit) {
        JsonManager(context).photos()?.let { photos.addAll(it) }

    }
    Column(modifier = Modifier.padding(10.dp    )) {
    LazyVerticalGrid(columns = GridCells.Fixed(if(horizontal) 3 else 2), horizontalArrangement = Arrangement.spacedBy(10.dp   ), verticalArrangement = Arrangement.spacedBy(10.dp  ) ) {
        items(6) {
            var openDialog by remember {mutableStateOf(false)}
            if(photos.size != 0 ) {
                var expanded by remember {mutableStateOf(false)}
                Box(modifier = Modifier
                    .size(300.dp)
                    .combinedClickable(onLongClick = { expanded = true }) {

                        openDialog = true

                    }
                    ) {

                    AsyncImage(model = photos[pagination * 6 + (it)].imageLink, contentDescription = "the bakground", modifier = Modifier.matchParentSize(), contentScale = ContentScale.Crop)
                    Column(modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .background(Color(0xAA000000))) {
                        Text(text ="Votes " + photos[pagination * 6 + (it)].votes, color = Color.White)
                        Text(text = "Popularity " + photos[pagination * 6 + it].popularity, color = Color.White)
                    }
                    val scope = rememberCoroutineScope()

                        DropdownMenu(expanded = expanded , onDismissRequest = { expanded = false     }) {
                            DropdownMenuItem(onClick = {
                                scope.launch {
                                    SendBinaryStream(context).send(photos[pagination * 6 + it].imageLink)
                                }


                            }) {
                                Text(text = "Share")
                            }
                        }

                }

                if(openDialog) {
                    Dialog(onDismissRequest = { openDialog = false }) {
                        var offsetR by remember {mutableStateOf(Offset.Zero)}
                        var x by remember {mutableStateOf(1.0f)}

                        var transformableState = rememberTransformableState { fl: Float, offset: Offset, fl1: Float ->
                            x *= fl
                            offsetR += offset
                        }
                        AsyncImage(model     = photos[pagination * 6 + it].imageLink, contentDescription =  "Backgroudn", modifier = Modifier
                            .fillMaxSize()
                            .offset(offsetR.x.dp, offsetR.y.dp)
                            .graphicsLayer {
                                this.scaleY = x
                                this.scaleX = x
                            }
                            .transformable(transformableState), contentScale = ContentScale.Crop)
                    }
                }
            }


        }

    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp , Alignment.CenterHorizontally)) {
        repeat(photos.size.floorDiv(6) ) {

            Text(text = (it + 1).toString(), fontSize = 18.sp, modifier = Modifier.padding(10.dp)
                .clickable {
                    pagination = it
                }, fontWeight = if(it == pagination )FontWeight.SemiBold else FontWeight.Normal
                )
        }
    }
}
}


