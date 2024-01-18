package com.example.international

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.international.presentation.EventDetailsScreen
import com.example.international.presentation.EventsListScreen
import com.example.international.presentation.RecordsScreen
import com.example.international.presentation.SplashScreen
import com.example.international.presentation.TicketCreateScreen
import com.example.international.presentation.TicketDetailsScreen
import com.example.international.presentation.TicketsListScreen
import com.example.international.ui.theme.InternationalTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigation = remember {listOf("events", "tickets", "records") }
            val navController = rememberNavController()
            InternationalTheme {
                Scaffold(bottomBar = {
                    if(navController.currentBackStackEntryAsState().value?.destination?.route != "splashScreen" ) {
                        NavigationBar {
                            navigation.forEach {
                                navController.currentBackStackEntryAsState().value?.destination?.route?.lowercase()?.contains(it   )
                                    ?.let { it1 ->
                                        NavigationBarItem(
                                            selected = it1,
                                            onClick = { navController.navigate(it) },
                                            icon = { Image(Icons.Default.Star, contentDescription = "")},
                                            label = { Text(text = it.capitalize())})
                                    }
                            }
                        }
                    }

                }) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(it)) {
                        NavHost(navController = navController, startDestination = "splashScreen") {
                            composable("splashScreen") {
                                SplashScreen(navController = navController)
                            }
                            composable("events") {
                                EventsListScreen(navController = navController)
                            }
                            composable("eventDetails/{id}") {
                                it.arguments?.getString("id")?.toInt()
                                    ?.let { it1 -> EventDetailsScreen(navController = navController, id = it1) }
                            }
                            composable("tickets") {
                                TicketsListScreen(navController = navController)
                            }
                            composable("ticketCreate") {
                                TicketCreateScreen(navController = navController)
                            }
                            composable("ticketDetails/{id}") {
                                TicketDetailsScreen(navController = navController, id = it.arguments?.getString("id")?.toInt())
                            }
                            composable("records") {
                                RecordsScreen(navController = navController)
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
    InternationalTheme {
        Greeting("Android")
    }
}