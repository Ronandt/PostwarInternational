package com.example.module_b_pm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.module_b_pm.ui.theme.Module_B_PMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Module_B_PMTheme {
                // A surface container using the 'background' color from the theme
                var shadow by remember { mutableStateOf(false) }

                val navController = rememberNavController()
                val navigationList = listOf("home", "skills", "photos", "videos")
                Box {

                    Row(modifier = Modifier.fillMaxSize()) {
                        NavigationRail {
                            Image(painter = painterResource(R.drawable.wordlskillslogo), contentDescription = "Logo")
                            Spacer(modifier = Modifier.height(20.dp))
                            navigationList.forEach {
                                Spacer(modifier = Modifier.height(10.dp))
                                NavigationRailItem(
                                    selected = navController.currentBackStackEntryAsState().value?.destination?.route == it,
                                    onClick = { navController.navigate(it)},
                                    label = {
                                        Text(text = it.capitalize())
                                    },

                                    icon = { when(it) {
                                        "home" -> {
                                            Icon(Icons.Default.Home, contentDescription = "Home" )
                                        }
                                        "skills" -> {
                                            Icon(Icons.Default.Build, contentDescription = "SKills")
                                        }

                                        "photos"  -> {
                                            Icon(Icons.Default.AccountBox, contentDescription ="image" )
                                        }
                                        "videos" -> {
                                            Icon(Icons.Default.PlayArrow, contentDescription = "Video")
                                        }
                                    } })
                            }
                        }
                        Column(modifier = Modifier.fillMaxSize()) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .background(Color.Black), verticalAlignment = Alignment.CenterVertically) {
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = navController.currentBackStackEntryAsState().value?.destination?.route.toString().capitalize(), color = Color.White, fontSize = 30.sp)
                            }
                            NavHost(navController = navController, startDestination = "home" ) {
                                composable("home") {
                                    HomeScreen(navController = navController)
                                }
                                composable("skills") {
                                    SkillsScreen(navController = navController) { bool: Boolean -> shadow =
                                        bool
                                    }
                                }
                                composable("photos") {
                                    PhotosScreen(navController = navController, {bool: Boolean -> shadow = bool})
                                }
                                composable("videos") {
                                    VideosScreen(navController = navController)
                                }

                            }
                        }

                    }
                    if(shadow) {
                        Spacer(modifier = Modifier.fillMaxSize().background(Color(0xA5000000)))
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
    Module_B_PMTheme {
        Greeting("Android")
    }
}