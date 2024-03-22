package com.example.youtubeclone

import android.inputmethodservice.Keyboard.Row
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.youtubeclone.ui.theme.DarkGray
import com.example.youtubeclone.ui.theme.Oswald
import com.example.youtubeclone.ui.theme.YoutubeCloneTheme
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {
    @androidx.annotation.OptIn(UnstableApi::class)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoutubeCloneTheme {


                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val scrollState = rememberLazyListState()
                var currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
                val navigationList = listOf("home", "shorts", "subscription", "profile")
                val filterList = listOf("All", "Music", "Gaming", "Fun", "Manga")

                var offsetBuffer = remember {mutableStateListOf<Int>(0,0)}
                val offset = remember {derivedStateOf { scrollState.firstVisibleItemScrollOffset.floorDiv(1)}}
                var totalOffsetBar by remember {mutableStateOf(0.dp)}
                var animate = animateDpAsState(targetValue = totalOffsetBar)
                LaunchedEffect(key1 =offset.value) {
                    println( scrollState.firstVisibleItemIndex)
                    if(scrollState.isScrollInProgress) {
                        offsetBuffer.add(offset.value)
                        offsetBuffer.removeFirst()
                        println(offsetBuffer.toList())
                        if((offsetBuffer[0] - offsetBuffer[1]) > 60) {
                            return@LaunchedEffect
                        }

                        if(offsetBuffer[0] < offsetBuffer[1]) {

                            totalOffsetBar -= 6.dp
                        } else {

                            totalOffsetBar +=6.dp
                        }
                        if(offsetBuffer[1] < 10) {
                            totalOffsetBar = 0.dp
                        }
                    }

                 //   totalOffsetBar += offsetBuffer[0] - offsetBuffer[1]).dp//.coerceIn(0.dp, 100.dp)


                }
                Column {
                    Scaffold(topBar = {
                                      if(currentDestination == "home" || currentDestination =="subscription") {
                                //          var offset = remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }

                                          TopAppBar(modifier = Modifier.offset(x = 0.dp, y = totalOffsetBar.coerceIn(-60.dp, 0.dp) ),actions = {
                                              Column {
                                                  Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                                                      IconButton(onClick = { /*TODO*/ }) {

                                                          Icon(
                                                              painter = painterResource(id = R.drawable.baseline_cast_24), contentDescription = "Cast on Tv", tint = Color.White
                                                          )
                                                      }

                                                      BadgedBox(badge = { Badge {
                                                          Text(text = "9")
                                                      }}, modifier = Modifier
                                                          .clickable { }
                                                          .offset(y = 12.dp)) {

                                                          Icon(
                                                              Icons.Outlined.Notifications, contentDescription = "Cast on Tv"
                                                              , tint = Color.White)


                                                      }
                                                      IconButton(onClick = { /*TODO*/ }) {

                                                          Icon(
                                                              Icons.Default.Search, contentDescription = "Search", tint = Color.White
                                                          )
                                                      }
                                                  }


                                              }



                                          },colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkGray), title = {
                                              Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
                                                  Image(painter = painterResource(R.drawable.youtube), contentDescription = "Youtube logo", contentScale = ContentScale.Crop, modifier = Modifier
                                                      .height(height = 30.dp)
                                                      .width(54.dp)
                                                      .clip(
                                                          RoundedCornerShape(10.dp)
                                                      ))
                                                  Text(text = "Premium", fontWeight = FontWeight.Medium, color = Color.White, fontFamily = Oswald)
                                              }
                                          })
                                      }

                    }, bottomBar = {

                        BottomAppBar(containerColor = DarkGray, contentPadding = PaddingValues(0.dp), modifier = Modifier.height(62.dp)) {
                       Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {

                           navigationList.forEach {

                               Column(modifier = Modifier
                                   .padding(horizontal = 16.dp, vertical = 4.dp)
                                   .clickable {
                                       navController.navigate(it) {
                                           this.launchSingleTop = true

                                       }
                                   }, verticalArrangement = Arrangement.spacedBy(6.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                  when(it) {
                                       "home" -> {
                                           if(currentDestination != it) 
                                               Icon(Icons.Outlined.Home, contentDescription ="Home", tint     = Color.White)
                                         else
                                               Icon(Icons.Filled.Home, contentDescription ="Home", tint     = Color.White)
                                           
                                       }
                                       "shorts" -> {
                                           if(currentDestination != it)
                                               Icon(painterResource(id = R.drawable.outline_featured_video_24), contentDescription ="Home", tint     = Color.White)
                                           else
                                               Icon(painterResource(id = R.drawable.baseline_featured_video_24), contentDescription ="Home", tint     = Color.White)
                                       }
                                       "subscription" -> {
                                           if(currentDestination != it)
                                               Icon(Icons.Outlined.PlayArrow, contentDescription ="Home", tint     = Color.White)
                                           else
                                               Icon(Icons.Filled.PlayArrow, contentDescription ="Home", tint     = Color.White)
                                       }
                                       "profile" -> {
                                           if(currentDestination != it)
                                               Icon(Icons.Outlined.Person, contentDescription ="Home", tint     = Color.White)
                                           else
                                               Icon(Icons.Filled.Person, contentDescription ="Home", tint     = Color.White)
                                       } else -> {
                                           
                                       }
                                   }
                                

                                   Text(text = it.capitalize(), color = Color.White, fontSize = 13.sp)
                               }
                           }
                       }


                        }
                    }) {
                        Column(modifier = Modifier) {
                            NavHost(navController = navController, startDestination = "home" ) {
                                composable("home") {
                                   HomeScreen(navController, lazyListState = scrollState)
                                }
                                composable("shorts") {
                                    ShortsScreen(navController)
                                }
                                composable("subscription") {
                                    SubscriptionScreen(navController)
                                }
                                composable("profile") {
                                    ProfileScreen(navController)
                                }


                            }
                        }
                    }

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    YoutubeCloneTheme {

    }
}