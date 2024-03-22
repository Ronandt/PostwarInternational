package com.example.worldskills_am_b

import android.media.browse.MediaBrowser.MediaItem
import android.media.session.PlaybackState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoScreen(navController: NavController) {
    val  context = LocalContext.current
    var comment by remember {mutableStateOf("")}
    var allVideos = remember {mutableStateListOf<Video>()}
    var change by remember {mutableStateOf<Boolean>(false)}
    val scope = rememberCoroutineScope()
    var playbackspeed by remember {mutableStateOf(1.0f)}
    var comments  = remember { mutableStateListOf<CommentData>()}
   //var currentMediaItem by remember {mutableStateOf<androidx.media3.common.MediaItem?>( null)}
    var player = remember { ExoPlayer.Builder(context).build() }
    var currentVideo by remember {mutableStateOf("0")}
    var playing by remember {mutableStateOf(true)}
    var uriData by remember {mutableStateOf<androidx.media3.common.MediaItem?>(null)}
    LaunchedEffect(change) {
        var jsonManager = JsonManager(context = context)
        allVideos.clear()
        jsonManager.videos()?.let { allVideos.addAll(it) }
       uriData = androidx.media3.common.MediaItem.fromUri(allVideos[currentVideo.toInt()].link.toUri())
        player.apply {

                setMediaItem(uriData!!)
                this.prepare()
                this.play()



        }
        val database = Database().getDb()
        comments.clear()
        comments.addAll(database.query<CommentData>("videoId == $0", currentVideo   ).find())
    }



TemplateContent(content = {
    //val controller =
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp  )) {
        Column(modifier = Modifier.width(800.dp ).combinedClickable(onLongClick = {
            if(playbackspeed == 1.0f) {
                playbackspeed = 2.0f
                player.setPlaybackSpeed(playbackspeed)


            } else {
                playbackspeed = 1.0f
                player.setPlaybackSpeed(playbackspeed)
            }

        },onDoubleClick = {
         if(!player.isPlaying) {
             player.play()

         } else {
             player.pause()


         }



        }) {

        }) {

            AndroidView(factory = {
                PlayerView(it).apply {
                    this.player = player

                }

            }, modifier = Modifier
                .width(800.dp)
                .height(400.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)) {
                androidx.compose.material3.Text(text = "Video title", color = Color.White, modifier = Modifier.padding(10.dp))

            }

            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(value = comment , onValueChange = {comment = it}, placeholder = { Text(
                    text = "Type your comment here ")

                }, modifier = Modifier.width(600.dp ))

                Button(onClick = {
                    scope.launch {
                        val database = Database().getDb()
                        database.write {
                            this.copyToRealm(CommentData().apply {
                                videoId = currentVideo
                                this.ipAddress = "172.123.234"
                                this.commentText = comment
                            })
                        }
                    }
                    change = !change

                }) {
                    androidx.compose.material.Text(text = "Publish")
                }
            }

            Row {
                androidx.compose.material.Text(text = "${comments.size} comments", modifier = Modifier.padding(8.dp))
            }
            LazyColumn {
                items(comments.size) {
                    Row(modifier = Modifier
                        .width(500.dp)
                        .padding(10.dp)) {
                        Column {
                            androidx.compose.material.Text(text = comments[it].ipAddress, color = Color.LightGray)
                            Text(text = comments[it].commentText)
                            Divider(modifier = Modifier.width(500.dp))
                        }

                    }
                }
            }
        }
     Column(modifier = Modifier.padding(10.dp)) {
         androidx.compose.material.Text(text = "More videos")
         LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp )) {
             items(allVideos.size) {
                 Box(modifier = Modifier.size(300.dp, 150.dp    ).clickable {
                     uriData = androidx.media3.common.MediaItem.fromUri(allVideos[it].thumbnail.toUri())
                     change = !change
                     currentVideo = allVideos[it].id
                 }) {
                     AsyncImage(model = allVideos[it].thumbnail, contentDescription = "Yes", contentScale = ContentScale.Crop, modifier = Modifier.matchParentSize())

                     findData(context, allVideos[it].link)?.let { it1 ->

                         androidx.compose.material.Text(text = ((it1.toLong()/1000).floorDiv(60).toString() + ":" +  ((it1.toLong()/1000) %60).toString().padStart(2, '0')), color  = Color.White, modifier = Modifier
                             .align(
                                 Alignment.BottomEnd
                             )
                             .background(Color.Black))
                     }

                 }
             }
         }
     }
    }





})


}