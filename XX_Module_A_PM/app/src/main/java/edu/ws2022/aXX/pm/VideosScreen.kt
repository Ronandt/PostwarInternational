package edu.ws2022.aXX.pm

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView


import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideosScreen(navController: NavController) {
    var videoState by remember{mutableStateOf("0")}
    var commentTextField by remember {mutableStateOf("")}
    var videos = remember {mutableStateListOf<Video>()}
    val context = LocalContext.current
    var comments = remember {mutableStateListOf<Comment>()}
    var speed by remember {mutableStateOf(1.0f)}
    var Mplayer = ExoPlayer.Builder(context).build()
    LaunchedEffect(Unit) {
        JsonManager(context = context).videos()?.let { videos.addAll(it) }

    }
    LaunchedEffect(Unit, videoState) {
        comments.clear()
        comments.addAll(Database().getDb().query<Comment>().find())
        var mediaItem = videos.find { it.id == videoState}?.link?.toUri()
            ?.let { MediaItem.fromUri(it) }?.let { Mplayer.setMediaItem(it) }
        Mplayer.playWhenReady = true
        Mplayer.play()

    }
Column(modifier = Modifier
    .fillMaxSize()
    .padding(10.dp).combinedClickable(onLongClick = {
        if(speed == 1.0f) {
            Mplayer.setPlaybackSpeed(2.0f   )
            speed = 2.0f
        } else {
            Mplayer.setPlaybackSpeed(1.0f   )
            speed = 1.0f
        }



    }, onDoubleClick = {
        if(Mplayer.isPlaying) {
            Mplayer.pause()
        } else {
            Mplayer.play()
        }

    }) {



    }) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp  )) {
        Column(modifier = Modifier.width(850.dp )) {
            val scope = rememberCoroutineScope()
            AndroidView(factory = {
                PlayerView(it).apply {
                    player = Mplayer
                }

            }, modifier = Modifier
                .fillMaxWidth()
                .height(500.dp))
            videos.find {it.id == videoState}?.title?.let { Text(text = it, modifier = Modifier.padding(10.dp), fontSize = 18.sp ) }
            Row {
                OutlinedTextField(value = commentTextField , onValueChange = {commentTextField = it}, placeholder = { Text(
                    text = "Type a new comment here"
                )}, modifier = Modifier.fillMaxWidth(0.8f))
                Button(onClick ={
                    scope.launch {
                        Database().getDb().write {
                            var comment = Comment().apply {
                                this.videoId = videoState
                                this.text = commentTextField
                                this.ipAddress = "182.334.234"
                            }
                            copyToRealm(comment)
                            comments.add(comment)
                        }
                        commentTextField = ""


                    }



                }) {
                    Text("Publish")
                }
            }
            LazyColumn {
                item {
                    Text(text =comments.size.toString() + " comments", fontSize = 20.sp )

                }
                items(comments.size) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "From ${comments[it].ipAddress}", color = Color.Gray)
                    Text(text = comments[it].text)
                }
            }
        }
        Column {
            Text(text = "More videos...")
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp )){
                items(videos.size) {
                    Box(modifier = Modifier
                        .width(240.dp)
                        .height(140.dp).clickable {
                            videoState = videos[it].id
                        }) {
                        AsyncImage(model = videos[it].thumbnail, contentDescription ="Painter resource", modifier = Modifier.matchParentSize(), contentScale = ContentScale.Crop)
                      Text(text = "1:30", modifier = Modifier
                          .align(Alignment.BottomEnd)
                          .padding(10.dp)
                          .background(Color.Black), color = Color.White)

                    }

                }
            }
        }
    }
}
}