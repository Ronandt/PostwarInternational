package com.example.healthapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.healthapp.ui.theme.Dark
import com.example.healthapp.ui.theme.HealthAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthAppTheme {
                val navController = rememberNavController()
                LaunchedEffect(Unit) {
                    println(API().register().toString())
                }
                var currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
                Scaffold(
                    bottomBar = {

                        if(currentDestination == "home" ||currentDestination == "alarm" ||  currentDestination == "workout" || currentDestination == "myPage") {
                            NavigationBar(containerColor = Dark){
                                NavigationBarItem(
                                    selected = currentDestination == "home",
                                    onClick = { navController.navigate("home")},
                                    icon = { Icon(painterResource(R.drawable.home_1_svgrepo_com), contentDescription = "Home", tint = Color.White, modifier = Modifier.size(32.dp )) },
                                    label = { Text(text = "Home", color = Color.White)}, colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent))


                                NavigationBarItem(
                                    selected = currentDestination == "alarm",
                                    onClick = { navController.navigate("alarm")},
                                    icon = { Icon(painterResource(R.drawable.alarm_clock_svgrepo_com), contentDescription = "Home", tint = Color.White, modifier = Modifier.size(32.dp )) },
                                    label = { Text(text = "Alarm", color = Color.White)}, colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent))


                                NavigationBarItem(
                                    selected = currentDestination == "workout",
                                    onClick = { navController.navigate("workout")},
                                    icon = { Icon(painterResource(R.drawable.run_on_treadmill_exercise_work_out_run_svgrepo_com), contentDescription = "Home", tint = Color.White, modifier = Modifier.size(32.dp )) },
                                    label = { Text(text = "Workout", color = Color.White)}, colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent))

                                NavigationBarItem(
                                    selected = currentDestination == "myPage",
                                    onClick = { navController.navigate("myPage")},
                                    icon = { Icon(painterResource(R.drawable.user_svgrepo_com), contentDescription = "Home", tint = Color.White, modifier = Modifier.size(32.dp )) },
                                    label = { Text(text = "My Page", color = Color.White)}, colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent))

                            }

                    }

                    }
                ) {
                    Column(modifier = Modifier.padding(it)) {
                        NavHost(navController = navController, startDestination = "splash" ) {
                            composable("splash") {
                                SplashScreen(navController)
                            }
                            composable("login") {
                                LoginScreen(navController)
                            }
                            composable("signUp") {
                                SignUpScreen(navController)
                            }
                            composable("passwordReset") {
                                PasswordResetScreen(navController)
                            }
                            composable("onboard") {
                                OnboardScreen(navController)
                            }
                            composable("home") {
                                HomeScreen(navController)
                            }
                            composable("alarm")  {
                                AlarmScreen(navController)
                            }
                            composable("workout") {
                                WorkoutScreen(navController)
                            }
                            composable("myPage") {
                                MyPageScreen(navController)
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
    HealthAppTheme {
        Greeting("Android")
    }
}