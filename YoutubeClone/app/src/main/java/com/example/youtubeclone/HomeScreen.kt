package com.example.youtubeclone

import android.os.CountDownTimer
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.youtubeclone.ui.theme.DarkGray


@OptIn(UnstableApi::class)
@Composable
fun HomeScreen(navController: NavController, lazyListState:LazyListState) {
    val applicationContext = LocalContext.current


Column(modifier = Modifier.background(DarkGray)) {

    LazyColumn(state = lazyListState) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
        items(5) {
            Column(modifier = Modifier.clickable {  }) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)) {
                    AnimatedContent(targetState = lazyListState.firstVisibleItemIndex == it, modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                        label = ""
                    ) {
                        val exoPlayer = remember {ExoPlayer.Builder(applicationContext).build().apply {
                            setMediaItem(MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.yuuri)))
                            playWhenReady = true
                            prepare()
                        }}
                        val checkDuration =remember {if(exoPlayer.duration < 0) 269254 else exoPlayer.duration}
                        val totalDuration = remember {checkDuration.floorDiv(60000).toString()  + ":" + (checkDuration - checkDuration.floorDiv(60000) * 60000).floorDiv(1000)}
                        if(it) {
                            var currentDuration by remember {mutableStateOf(totalDuration)}
                            val countdown = remember {object: CountDownTimer(checkDuration, 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    println(millisUntilFinished)
                                    println("WHY")
                                    currentDuration  = millisUntilFinished.floorDiv(60000).toString()  + ":" + (millisUntilFinished- millisUntilFinished.floorDiv(60000) * 60000).floorDiv(1000).toString().padStart(2, '0')
                                }

                                override fun onFinish() {

                                }
                            }}
                            LaunchedEffect(key1 = Unit ) {
                                countdown.cancel()
                                countdown.start()
                                println("NO START?")

                            }
                            Box(modifier = Modifier.matchParentSize()) {

                                AndroidView(factory = {
                                    PlayerView(it).apply {
                                        player = exoPlayer
                                    }
                                }, modifier = Modifier.matchParentSize())
                              Column(modifier = Modifier
                                  .padding(10.dp)
                                  .align(Alignment.TopEnd)
                                  .padding(top = 10.dp)) {
                                   IconButton(onClick = { /*TODO*/ }) {
                                       Icon(painterResource(id = R.drawable.baseline_volume_off_24), contentDescription = "Volume off", tint = Color.White, modifier = Modifier.size(26.dp))
                                   }
                                  IconButton(onClick = { /*TODO*/ }) {
                                      Icon(painterResource(id = R.drawable.baseline_subtitles_24), contentDescription = "Subtitles", tint = Color.White, modifier = Modifier.size(26.dp))
                                  }
                               }
                                Text(text = currentDuration, color = Color.White, modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(10.dp)
                                    .drawBehind {
                                        this.drawRoundRect(
                                            Color(0xBB000000),
                                            size = Size(85f, 50f),
                                            cornerRadius = CornerRadius(10f, 10f)
                                        )
                                    }, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                            }

                        } else {
                            LaunchedEffect(key1 = Unit) {
                                exoPlayer.stop()
                            }
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp)) {
                            Image(painter = painterResource(R.drawable.first_take_yuuri), contentDescription = "thumbnail", contentScale = ContentScale.Crop, modifier = Modifier.matchParentSize())
                            Text(text = totalDuration, color = Color.White, modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(10.dp)
                                .drawBehind {
                                    this.drawRoundRect(
                                        Color(0xBB000000),
                                        size = Size(85f, 50f),
                                        cornerRadius = CornerRadius(10f, 10f)
                                    )
                                }, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                        }}
                    }




                }
                Column(modifier = Modifier.padding(bottom = 20.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
                        .padding(4.dp)
                        .padding(top = 6.dp)) {
                        Image(painter = painterResource(id = R.drawable.first_take_yuuri), contentDescription = "Yuuri", modifier = Modifier
                            .size(48.dp)
                            .clip(
                                CircleShape
                            ), contentScale = ContentScale.Crop)
                        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                            Text(text = "Yuuri - The First Take Betelguese. ", color =Color.White, fontWeight = FontWeight.Medium, lineHeight = 18.sp)
                            Text(text = "The First Take • 121k Views • 1 year ago", color = Color.Gray, fontSize = 13.sp)
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More", tint= Color.White)
                        }

                    }

                }
            }

         
        }
        items(1) {
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}
}