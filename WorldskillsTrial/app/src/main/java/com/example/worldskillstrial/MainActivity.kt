package com.example.worldskillstrial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.worldskillstrial.ui.theme.WorldskillsTrialTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            if(intent.data != null) {
                navController.navigate(intent.data!!)
            }
            var navControllerRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            WorldskillsTrialTheme {
                Scaffold(bottomBar = {
                    if(navControllerRoute != "splash") {
                        NavigationBar {

                                NavigationBarItem(
                                    selected = navControllerRoute =="events",
                                    onClick = { navController.navigate("events") },
                                    icon = {Icon(painterResource(R.drawable.baseline_home_24), contentDescription = null) })
                            NavigationBarItem(
                                selected = navControllerRoute == "tickets",
                                onClick = { navController.navigate("tickets") },
                                icon = {Icon(painterResource(R.drawable.baseline_post_add_24), contentDescription = null) })
                            NavigationBarItem(
                                selected = navControllerRoute == "records",
                                onClick = { navController.navigate("records") },
                                icon = {Icon(painterResource(R.drawable.baseline_fiber_smart_record_24), contentDescription = null) })

                        }
                    }
                }) {
                    Column(modifier = Modifier.padding(it)) {
                        NavHost(navController = navController, startDestination= "splash" ) {
                            composable("splash") {
                                SplashScreen(navController)
                            }
                            composable("events") {
                                EventsScreen(navController)
                            }
                            composable("tickets") {
                                TicketsScreen(navController)
                            }
                            composable("records") {
                                RecordsScreen(navController)
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
    WorldskillsTrialTheme {
        Greeting("Android")
    }
}