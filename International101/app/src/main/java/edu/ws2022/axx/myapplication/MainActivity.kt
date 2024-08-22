package edu.ws2022.axx.myapplication

import android.media.PlaybackParams
import android.os.Build
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.ws2022.axx.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
                    val navigationList = remember {listOf("home", "videos", "skills", "photos")}

                    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(4.dp    )) {
                        NavigationRail {
Image(painter = painterResource(id = R.drawable.wordkskills), contentDescription = "logo", modifier = Modifier.size(80.dp   ))
                            Spacer(modifier = Modifier.height(10.dp))
                            navigationList.forEach {
                                NavigationRailItem(selected = currentDestination==it, onClick = { navController.navigate(it) }, label ={ Text(
                                    text = it.capitalize()
                                ) }, icon = {
                                    when(it) {
                                        "home" -> {
                                            Icon(imageVector = Icons.Default.Home, contentDescription = "HOome" )
                                        }
                                        "videos"-> {
                                            Icon(painter =  painterResource(id = R.drawable.baseline_video_camera_front_24), contentDescription = "Vidoe" )
                                        }
                                        "skills"-> {
                                            Icon(painter = painterResource(id = R.drawable.baseline_downhill_skiing_24), contentDescription = "Skills" )
                                        }
                                        "photos" -> {
                                            Icon(painter = painterResource(id = R.drawable.baseline_photo_library_24), contentDescription = "Photos")
                                        }
                                    }
                                })
                            }
                        }
                        Column(modifier = Modifier.padding(10.dp    )) {
                            Row(modifier  = Modifier
                                .fillMaxWidth()
                                .background(Color.Black)) {
                                if (currentDestination != null) {
                                    Text(modifier = Modifier.padding(10.dp  ),text = currentDestination.capitalize(), fontWeight = FontWeight.SemiBold, fontSize = 30.sp  , color = Color.White)
                                }
                            }
                            NavHost(navController = navController, startDestination = "home" ) {
                                composable("home") {
                                    HomeScreen(navController)
                                }
                                composable("videos") {
                                    VideosScreen(navController)
                                }
                                composable("skills") {
                                    SkillsScreen(navController)
                                }
                                composable("photos") {
                                    PhotosScreen(navController)
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
    MyApplicationTheme {
        Greeting("Android")
    }
}