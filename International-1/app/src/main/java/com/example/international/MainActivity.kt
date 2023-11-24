package com.example.international

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.international.presentation.HomeScreen
import com.example.international.presentation.PhotosScreen
import com.example.international.presentation.SkillsScreen
import com.example.international.presentation.VideosScreen
import com.example.international.ui.theme.International1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var position = remember  {mutableStateOf(0.dp)}
            val content = remember { listOf("home", "skills", "photos", "videos")}
          val navController  = rememberNavController()
            International1Theme {
                Box {
                    Row(
                        Modifier
                            .align(Alignment.TopCenter)
                            .fillMaxWidth().height(70.dp)
                            .background(Color.Black).onGloballyPositioned {
                                position.value = it.size.height.dp
                            }) {
                        Text(text = "Skills list", color = Color.White, modifier = Modifier.align(Alignment.CenterVertically).offset(x = 100.dp), fontSize = 30.sp)
                    }
                    Row(modifier = Modifier.fillMaxSize(

                    )) {


                        NavigationRail {

                            content.forEach {

                                Spacer(modifier = Modifier.height(20.dp))
                                NavigationRailItem(
                                    selected = navController.currentBackStackEntryAsState().value?.destination?.route == it,
                                    onClick = { navController.navigate(it)},
                                    icon = { when(it) {
                                        "home" -> Icon(Icons.Default.Home, contentDescription =  " Home", modifier = Modifier.size(24.dp))

                                        "skills" -> Icon(Icons.Default.Build, contentDescription =  " Home", modifier = Modifier.size(24.dp))

                                        "photos" -> Icon(painterResource(R.drawable.baseline_insert_photo_24), contentDescription =  " Home", modifier = Modifier.size(24.dp))

                                        "videos" ->  Icon(painterResource(R.drawable.baseline_videocam_24), contentDescription =  " Home", modifier = Modifier.size(24.dp))
                                        else ->  Icon(Icons.Default.Info, contentDescription ="Info" , modifier = Modifier.size(24.dp))

                                    }}, label = {
                                        Text(text = it.capitalize(), fontSize = 18.sp)
                                    })
                            }
                        }
                        NavHost(navController = navController, startDestination= "home", modifier = Modifier.offset(y = position.value)) {
                            composable("home") {
                                HomeScreen(navController = navController)
                            }
                            composable("photos") {
                                PhotosScreen(navController = navController)
                            }
                            composable("skills") {
                                SkillsScreen(navController = navController)
                            }
                            composable("videos") {
                                VideosScreen(navController = navController)
                            }
                        }
                    }
                }

                // A surface container using the 'background' color from the theme

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
    International1Theme {
        Greeting("Android")
    }
}