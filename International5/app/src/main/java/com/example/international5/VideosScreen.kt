package com.example.international5

import android.content.Context
import android.media.browse.MediaBrowser.MediaItem
import android.net.wifi.WifiManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import java.util.Formatter


@Composable
fun VideosScreen(navController: NavController) {
    var commentState by remember {mutableStateOf("")}
    val context = LocalContext.current
    var videos = remember {mutableStateListOf<VideoItem>() }
    var comments = remember {mutableStateListOf<CommentItem>()}
    var scope = rememberCoroutineScope()
    var change by remember {mutableStateOf(false)}
    var video by remember { mutableStateOf<VideoItem?>(null)}
    var videoId by remember {mutableStateOf("1")}
    var playerX by remember {mutableStateOf<ExoPlayer?>(null) }

    LaunchedEffect(change, videoId) {
        var data = WorldSkillsAPI().getVideos()
        videos.clear()
        if (data != null) {
            videos.addAll(data.shuffled().toList())
        }
        playerX = null
        playerX = ExoPlayer.Builder(context).build()
        comments.clear()
        video =  data?.filter {it.id == videoId}?.get(0)
        video?.comments?.let { comments.addAll(it) }
        println(data)
        println(comments)
        val mediaItem = video?.link?.let { androidx.media3.common.MediaItem.fromUri(it) }

        if (mediaItem != null) {
            playerX?.setMediaItem(mediaItem)
        }
        playerX?.prepare()
        playerX?.play()
    }
    Row(modifier = Modifier.fillMaxSize()) {
        Column {
            AndroidView(factory = {
                PlayerView(it).apply {
                   this.player = playerX

                }
            }, modifier = Modifier
                .height(400.dp)
                .width(850.dp), update = {
                    it.player = playerX
            })
            Spacer(modifier = Modifier.height(20.dp))
            video?.title?.let { Text(text = it, fontSize = 24.sp) }
            Spacer(modifier = Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.width(850.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(value = commentState, onValueChange = {commentState = it}, label =  {Text("Type in your comment here")}, modifier = Modifier.width(700.dp))
                Button(onClick = {
                    if(commentState != "") {
                        scope.launch {

                            val wm = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
                            val ip: String = android.text.format.Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
                            val comment = CommentItem(ipAddress = ip, text = commentState)
                            WorldSkillsAPI().insertComment(comment, videoId)
                            comments.add(comment)
                            commentState = ""
                        }

                    }


                }) {
                    Text(text = "Publish")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                item {
                    Text(text = comments.size.toString() + " Comments" , fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    
                }
                items(comments.size) {
                    Column(modifier = Modifier.width(850.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(text = comments[it].ipAddress, color = Color.Gray)
                        Text(text = comments[it].text, fontSize = 16.sp)
                        Divider()
                    }
                }
            }
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(10.dp)) {
            item {
                Text(text = "More videos")

             
            }
            items(videos.size) {
                Spacer(modifier = Modifier.height(5.dp))
                if(videos[it].id != videoId) {
                    Column(modifier = Modifier
                        .height(200.dp)
                        .width(250.dp)) {
                        Box(modifier = Modifier
                            .size(250.dp, 150.dp)
                            .clickable {

                                playerX?.release()
                                videoId = videos[it].id

                            }) {
                            AsyncImage(videos[it].thumbnail, contentDescription = "Background", modifier = Modifier.matchParentSize(), contentScale = ContentScale.Crop)
                            Text(text = "1:30", modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(Color(0xAA0000000)), color = Color.White)

                        }
                        Text(videos[it].title, modifier = Modifier
                            .width(250.dp)
                            .height(20.dp), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }

                }

            }
        }
    }
}