package com.example.international5

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.international5.ui.theme.DarkBlue
import com.example.international5.ui.theme.International5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            International5Theme {
                var navList = listOf("home", "skills", "photos", "videos")
                val navController = rememberNavController()
                val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
                Row(modifier = Modifier.fillMaxSize()) {
                    NavigationRail {
                        Image(painter = painterResource(R.drawable.wslyon), contentDescription = "Logo", modifier = Modifier
                            .size(130.dp, 110.dp)
                            .padding(20.dp), contentScale = ContentScale.Crop)
                        navList.forEach {

                            NavigationRailItem(
                                selected = it == currentDestination,
                                onClick = { navController.navigate(it)},
                                icon = {
                                    when(it) {
                                        "home" -> {
                                            Icon(imageVector = Icons.Default.AddCircle, contentDescription ="Icon" )
                                        }
                                        "skills" -> {
                                            Icon(imageVector = Icons.Default.Build, contentDescription ="Icon" )
                                        }
                                        "photos" -> {
                                            Icon(painterResource(id = R.drawable.baseline_photo_library_24), contentDescription ="Icon" )
                                        }
                                        "videos" -> {
                                            Icon(painterResource(id = R.drawable.baseline_video_camera_back_24), contentDescription = "Icon")
                                        }
                                    }

                                   },
                                label = {
                                    Text(it.capitalize(), modifier = Modifier.padding(start = 10.dp))
                                })
                        }
                    }
                    Column() {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .background(DarkBlue), verticalArrangement = Arrangement.Center) {

                            currentDestination?.capitalize()?.let { Text(text = it, fontSize = 30.sp , color = Color.White) }

                        }
                        val observer = InternetResolver(context = applicationContext).observe().collectAsState(
                            initial = false
                        )
                        // A surface container using the 'background' color from the theme
                        NavHost(navController, startDestination = "home") {
                            composable("home", deepLinks = listOf(
                                navDeepLink {
                                    uriPattern = "https://static-website345gh.s3.ap-southeast-1.amazonaws.com/index.html"
                                    action = Intent.ACTION_VIEW
                                }
                            )) {
                                HomeScreen(navController)

                            }
                            composable("skills") {
                                AnimatedContent(targetState = observer.value) {
                                    if(it) {
                                        SkillsScreen(navController)
                                    } else {
                                        NoInternetScreen()
                                    }
                                }


                            }
                            composable("photos") {
                                AnimatedContent(targetState = observer.value) {
                                    if(it) {
                                        PhotosScreen(navController)
                                    } else {
                                        NoInternetScreen()
                                    }
                                }
                            }
                            composable("videos") {
                                AnimatedContent(targetState = observer.value) {
                                    if(it) {
                                        VideosScreen(navController)
                                    } else {
                                        NoInternetScreen()
                                    }
                                }
                            }


                        }
                    }

                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    International5Theme {
        Greeting("Android")
    }
}