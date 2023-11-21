package com.example.myapplication.presentation


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getString
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                var navController = rememberNavController()
                val navigationBarList = listOf("Events", "Tickets", "Records")
             var currentDestination =navController.currentBackStackEntryAsState().value?.destination
             
//theoretically it should be the second last because that's the max the navgiaotn item gonna appear and handle by right
                Scaffold(bottomBar = {
                    NavigationBar {

                        navigationBarList.forEach { barListString ->
                            NavigationBarItem(selected =  currentDestination?.hierarchy?.any { barListString.lowercase() == it.route } == true, onClick = { navController.navigate(barListString.lowercase())}, icon = {
                              when(barListString) {
                                  "Events" -> {
                                      Icon(Icons.Default.Star, barListString)
                                  }
                                  "Tickets" -> {
                                      Icon(Icons.Default.FavoriteBorder, barListString)
                                  }
                                  "Records" -> {
                                      Icon(Icons.Default.Refresh, barListString)
                                  }
                              }

                            }, label = { Text(
                                text = barListString
                            )}, colors = NavigationBarItemDefaults.colors(selectedIconColor = Color.White, selectedTextColor = Color.Red, indicatorColor = Color.Red))
                        }
                    }
                }) {
                    Column(modifier = Modifier.padding(it)) {

                        NavHost(navController = navController, startDestination = "events" ) {
                            navigation(startDestination = "eventsList", route = "events") {
                                composable("eventsList") {
                                    EventsListScreen(navController = navController)
                                }
                                composable("eventsDetails/{id}") {
                                    EventsDetailsScreen(navController = navController,  it.arguments?.getString("id")?.toIntOrNull())
                                }
                            }
                            navigation(startDestination = "ticketList", route = "tickets") {
                                composable("ticketCreate") {
                                    TicketCreateScreen(navController = navController)
                                }
                                composable("ticketDetails/{id}", ) {
                                    it.arguments?.getString("id")?.toLong()?.let { it1 ->
                                        TicketDetailsScreen(navController = navController,
                                            it1
                                        )
                                    }
                                }
                                composable("ticketList") {
                                    TicketsListScreen(navController = navController)
                                }

                            }
                            composable("records") {
                                RecordsScreen(navController = navController)
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