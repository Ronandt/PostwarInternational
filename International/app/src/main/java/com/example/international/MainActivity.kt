package com.example.international

import android.app.Activity
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.international.ui.theme.HotPink
import com.example.international.ui.theme.InternationalTheme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InternationalTheme {
               val navController = rememberNavController()
                val view = LocalView.current
                val navList = remember {listOf("about", "statistic", "photos", "skills")}
                val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
                LaunchedEffect(key1 = Unit ) {
                    navController.currentBackStackEntryFlow.collectLatest {
                        val window = (view.context as Activity).window
                        when(it.destination.route){
                            "splash" ->{
                                window.statusBarColor = HotPink.toArgb()
                            } else -> {
                            window.statusBarColor = Color.White.toArgb()
                            }
                        }

                    }
                }
                Row(modifier = Modifier.fillMaxSize()) {
                    if(currentDestination != "splash") {
                        Column(modifier = Modifier
                            .fillMaxHeight()
                            .width(250.dp)
                            .padding(vertical = 14.dp)) {
                            Image(painter = painterResource(R.drawable.worldskillslogo), contentDescription = "worldskills logo", contentScale = ContentScale.Crop, modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .height(130.dp))
                            Spacer(modifier = Modifier.height(30.dp))
                            navList.forEach {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(IntrinsicSize.Min)
                                    .clickable {
                                        navController.navigate(it)
                                    }) {

                                    Text(it.capitalize(), fontWeight = FontWeight.SemiBold, modifier = Modifier
                                        .padding(vertical = 10.dp)
                                        .align(Alignment.Center), color =    if(currentDestination == it) Color.Black else Color.LightGray)
                                    if(currentDestination == it) {
                                        Divider(modifier = Modifier
                                            .fillMaxHeight()
                                            .align(Alignment.CenterStart)
                                            .width(3.dp), color = HotPink)
                                    }

                                }

                            }



                        }
                    }

                    Column {
                        var swipeState = rememberSwipeableState(0)


                        if(currentDestination != "splash") {
                            if(currentDestination!= null) {
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(text = currentDestination.toString().capitalize(), fontWeight = FontWeight.Black, fontSize = 26.sp)
                                Spacer(modifier = Modifier.height(10.dp))
                                Divider(
                                    color = HotPink,
                                    thickness = 5.dp,
                                    modifier = Modifier.width(16.dp)
                                )
                            }

                        }

                        NavHost(navController = navController, startDestination = "splash") {
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