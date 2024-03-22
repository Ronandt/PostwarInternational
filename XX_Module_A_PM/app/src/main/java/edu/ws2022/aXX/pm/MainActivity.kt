package edu.ws2022.aXX.pm

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.ws2022.aXX.pm.theme.WorldskillsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorldskillsTheme {
               val navController = rememberNavController()
                val  destination = navController.currentBackStackEntryAsState().value?.destination?.route
                val listNav = listOf("home", "skills", "photos", "videos")
                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    Column {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            ) {
                            NavigationRail {
                                Image(painter = painterResource(R.drawable.worldskills_logo), contentDescription = "wordlskills logo", contentScale = ContentScale.Crop, modifier = Modifier.size(30.dp ))
                                listNav.forEach {
                                    NavigationRailItem(
                                        selected = destination == it,
                                        onClick = { navController.navigate(it) },
                                        label = {
                                            Text(it.capitalize())
                                        },
                                        icon = {

                                            when (it) {
                                                "home" -> {
                                                    Icon(
                                                        Icons.Default.Home,
                                                        contentDescription = null
                                                    )
                                                }

                                                "skills" -> {
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.baseline_build_24),
                                                        contentDescription = null
                                                    )
                                                }

                                                "photos" -> {
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.baseline_photo_library_24),
                                                        contentDescription = null
                                                    )
                                                }

                                                "videos" -> {
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.baseline_video_call_24),
                                                        contentDescription = null
                                                    )
                                                }
                                            }

                                        })
                                }
                            }

                            Column {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Black)){
                                    Text(text =destination.toString().capitalize(), modifier = Modifier.padding(10.dp   ), color = Color.White)
                                }

                                NavHost(navController = navController, startDestination = "home") {
                                    composable("home") {
                                        HomeScreen(navController)
                                    }
                                    composable("skills") {
                                        SkillsScreen(navController)
                                    }
                                    composable("photos") {
                                        PhotosScreen(navController)
                                    }
                                    composable("videos") {
                                        VideosScreen(navController)
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
    WorldskillsTheme {
        Greeting("Android")
    }
}