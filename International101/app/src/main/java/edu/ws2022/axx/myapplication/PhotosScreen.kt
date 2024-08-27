package edu.ws2022.axx.myapplication

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosScreen(navController: NavController) {
    var selection by remember {mutableStateOf(0)}
    val scope  = rememberCoroutineScope()
    val listOfPhotos = remember {mutableStateListOf<Photo>()}
    val api = remember {API()}
    val context = LocalContext.current
    val shareBinary =  remember {ShareBinary(context)}
    LaunchedEffect(Unit) {
        println(api.photos())
        listOfPhotos.addAll(api.photos())

    }


Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        LazyHorizontalGrid(rows = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(10.dp    ), verticalArrangement = Arrangement.spacedBy(10.dp ) , modifier = Modifier.height(650.dp   )) {
            items(6) {

                var dialog by remember {mutableStateOf(false)}
                var longClick by remember {mutableStateOf(false)}
                if(dialog) {
                    var offset by remember {mutableStateOf(Offset.Zero)}
                    var zoom by remember {mutableStateOf(1.0f)}
                    var transformable = rememberTransformableState(onTransformation = { fl: Float, offsetl: Offset, fl1: Float ->
                     offset += offsetl
                        zoom *= fl
                    })

                    Dialog(onDismissRequest = { dialog  = false}) {
                     AsyncImage(model = listOfPhotos[selection * 6 + it].imageLink, contentDescription = "Image", modifier = Modifier
                         .fillMaxSize()
                         .transformable(transformable)
                         .graphicsLayer {
                             this.scaleX = zoom
                             this.scaleY = zoom
                             this.translationX = offset.x
                             this.translationY = offset.y
                         }, contentScale = ContentScale.Crop , )
                    }
                }
                if(listOfPhotos.size > 6 * selection + it) {
                    Box(modifier = Modifier.size(350.dp)) {
                        AsyncImage(if(listOfPhotos.isEmpty()) null else listOfPhotos[selection * 6 + it].imageLink, contentDescription = "Background", modifier = Modifier
                            .matchParentSize()
                            .combinedClickable(onClick = { dialog = true },
                                onLongClick = { longClick = true })
                            , contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier
                            .padding(14.dp)
                            .align(Alignment.BottomEnd)
                            .background(Color.Black)) {
                            Text(text = if(listOfPhotos.isEmpty()) "" else "Votes: ${listOfPhotos[selection * 6 + it].votes}", color = Color.White)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = if(listOfPhotos.isEmpty()) "" else "Popularity: ${listOfPhotos[selection * 6 + it].popularity}", color = Color.White)
                        }
                        DropdownMenu(expanded = longClick, onDismissRequest = { longClick = false    }) {
                            DropdownMenuItem(onClick = {
                                scope.launch {
                                    shareBinary.shareBinary(url = listOfPhotos[selection * 6 + it].imageLink)
                                }

                            }) {
                                Text(text = "Share")
                            }
                        }
                    }
                }

            }
        }
    }

Spacer(modifier = Modifier.height(10.dp))
    Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        repeat(Math.ceil(listOfPhotos.size / 6.0).toInt()) {
            Text(text = (it + 1).toString(), modifier = Modifier
                .clickable {
                    selection = it
                }
                .background(if (it == selection) Color.LightGray else Color.Transparent)
                .padding(6.dp))
        }
    }

}
}