package com.example.international

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection.Companion.In
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.international.ui.theme.InternationalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InternationalTheme {
                // A surface container using the 'background' color from the theme
                val navController  = rememberNavController()
                var navigationList = remember {listOf("about", "statistic", "photos", "skills")}

                Row(modifier = Modifier.fillMaxSize()) {
                    if(navController.currentBackStackEntryAsState().value?.destination?.route != "splash") {
                        Column(modifier = Modifier
                            .fillMaxHeight()
                            .width(240.dp)
                            .background(Color.White)) {

                            Image(painter = painterResource(R.drawable.worldskills), contentDescription = "Worldskills", modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.Crop)
                            navigationList.forEach {

                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min)
                                    .clickable {
                                        navController.navigate(it)
                                    }) {
                                    Spacer(modifier = Modifier.height(40.dp))
                                    if(it == navController.currentBackStackEntryAsState().value?.destination?.route) {
                                        Divider(modifier = Modifier
                                            .width(2.dp)
                                            .fillMaxHeight()
                                            .align(Alignment.CenterStart), color = Color(0xFFD51067), )
                                    }



                                    Text(text = it.capitalize(),fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.Center), color = if(it == navController.currentBackStackEntryAsState().value?.destination?.route) Color.Black else Color.LightGray)
                                    Spacer(modifier = Modifier.height(40.dp))
                                }
                            }

                        }
                    }

                    NavHost(navController = navController, startDestination= "splash") {
                        composable("splash") {
                            SplashScreen(navController)
                        }

                        composable("about") {
                            AboutScreen(navController)
                        }
                        composable("statistic") {
                            StatisticScreen(navController)
                        }
                        composable("photos") {
                            PhotosScreen(navController)
                        }
                        composable("skills") {
                            SkillsScreen(navController)
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
    InternationalTheme {
        Greeting("Android")
    }
}