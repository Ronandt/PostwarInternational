package com.example.worldskills_am_b

import android.content.Intent
import android.os.Environment
import android.provider.ContactsContract.CommonDataKinds.Website.URL
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosScreen(navController: NavController) {
    val context = LocalContext.current
    var photos = remember {mutableStateListOf<Photo>()}
    var paginationState by remember {mutableStateOf(0)}
    LaunchedEffect(Unit) {
        val jsonManager = JsonManager(context = context)
        jsonManager.photos()?.let { photos.addAll(it) }
    }
    val localview = LocalView.current
    val scope =rememberCoroutineScope()
    var selected by remember {mutableStateOf(0)}
    TemplateContent({

        LazyVerticalGrid(columns = GridCells.Fixed(if(localview.width > localview.height)3 else 2), horizontalArrangement = Arrangement.spacedBy(10.dp    ), verticalArrangement = Arrangement.spacedBy(10.dp ) ) {
            items(6) {
                var offset by remember {mutableStateOf<Offset>(Offset.Zero)}
                var zoomX by remember {mutableStateOf(1.0f)}
                var zoomY by remember {mutableStateOf(1.0f)}
                var transformableState = rememberTransformableState { fl: Float, o: Offset, fl1: Float ->
                    offset += o
                   zoomX *= fl

                }
                if(photos.size != 0) {
                    var longClick by remember {mutableStateOf(false)}
                    var openDialog by remember {mutableStateOf(false)}
                    if(openDialog) {
                        Dialog(onDismissRequest = { openDialog = false }) {
                            AsyncImage(model = photos[ (paginationState*6) + it ].imageLink, contentDescription = "Photo of worldskills" , contentScale = ContentScale.Crop, modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    this.scaleX *= zoomX
                                    this.scaleY *= zoomX
                                }
                                .offset(x = offset.x.dp, y = offset.y.dp)
                                .transformable(transformableState))
                        }
                    }
                    Box(modifier = Modifier
                        .size(300.dp)
                        .combinedClickable(onLongClick = {
                            longClick = true
                        }) {

                            openDialog = true
                        }) {

                            DropdownMenu(expanded = longClick , onDismissRequest = { longClick = false}) {
                                DropdownMenuItem(text = { Text(text = "Share")}, onClick = {
                                    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "/tmp")
                                    scope.launch {
                                        withContext(Dispatchers.IO) {
                                        //    URL(photos[it* (paginationState+1)])
                                            URL( photos[(paginationState*6) + it].imageLink).openStream().copyTo(file.outputStream())
                                            val shareIntent = Intent().apply {
                                                action = Intent.ACTION_SEND
                                                type = "image/*"
                                                putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context, context.applicationContext.packageName + ".provider", file))

                                            }
                                            context.startActivity(Intent.createChooser(shareIntent,null))

                                        }
                                    }
                                })
                            }

                        AsyncImage(model = photos[ (paginationState*6) + it ].imageLink, contentDescription = "Image here", modifier = Modifier.matchParentSize(), contentScale = ContentScale.Crop)
                        Column(modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .background(Color(0xAA000000))) {
                            LaunchedEffect(Unit) {
                               println( it* (paginationState+1)) //or 1,2,3,4,5,6
                            }
                            Text("Popularity: ${photos[  (paginationState*6) + it ].popularity}", color = Color.White     )
                            Text("Votes ${photos[ (paginationState*6) + it ].votes}", color = Color.White)
                        }
                    }
                }


            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                repeat(photos.size .floorDiv(6)) {
                    Text(text = (it+1).toString(), modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            paginationState = it
                        })
                }
            }

        }




    })


}