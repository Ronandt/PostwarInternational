package com.example.worldskills_am_b

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.worldskills_am_b.ui.theme.Worldskills_AM_BTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Worldskills_AM_BTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                var destination = navController.currentBackStackEntryAsState().value?.destination?.route
                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(15.dp   )) {
                    NavigationRail {
                       Image(painter = painterResource(R.drawable.worldskillslogo), contentDescription = "Worldskills logo", contentScale = ContentScale.Crop, modifier = Modifier.size(50.dp   ))
                        listOf("home", "skills", "photos", "videos").forEach {
                            NavigationRailItem(
                                selected = it == destination,
                                onClick = { navController.navigate(it)},
                                icon = {
                                    var iconData = when(it) {
                                        "home" -> {
                                            Icons.Default.Home
                                        }
                                        "skills" -> {
                                            Icons.Default.Build
                                        }
                                        "photos" -> {
                                            Icons.Default.LocationOn
                                        }
                                        "videos" -> {
                                            Icons.Default.Face
                                        }
                                        else -> {
                                            Icons.Default.Home
                                        }
                                    }

                                    Icon(imageVector = iconData, contentDescription = "Here is the icon")

                                })
                        }
                    }
                    Scaffold() {
                        Column(modifier = Modifier.padding(it   )) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .background(Color.Black)) {
                                destination?.capitalize()?.let { it1 ->
                                    Text(
                                        text = it1,
                                        color = Color.White,
                                        modifier = Modifier.padding(8.dp), fontSize = 30.sp
                                    )
                                }
                            }
                            NavHost(navController = navController, startDestination = "home") {
                                composable("home") {
                                    HomeScreen(navController)
                                }
                                composable("skills") {
                                    SkillScreen(navController)
                                }
                                composable("photos") {
                                    PhotosScreen(navController)
                                }
                                composable("videos") {
                                    VideoScreen(navController)
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
    Worldskills_AM_BTheme {
        Greeting("Android")
    }
}