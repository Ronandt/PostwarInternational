package com.example.worldskillsa_am_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.worldskillsa_am_2.ui.theme.WorldskillsA_AM_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var destination = navController.currentBackStackEntryAsState().value?.destination?.route
            Scaffold(bottomBar = {
                if(destination != "splash") {
                    NavigationBar {

                        NavigationBarItem(
                            selected = destination == "events" || destination == "eventDetails",
                            onClick = { navController.navigate("events") },
                            icon = {
                                Icon(
                                    painterResource(R.drawable.baseline_home_24),
                                    contentDescription = "Events"
                                )
                            })
                        NavigationBarItem(
                            selected = destination == "tickets" || destination == "ticketDetails" || destination == "ticketCreate",
                            onClick = { navController.navigate("tickets") },
                            icon = {
                                Icon(
                                    painterResource(R.drawable.baseline_post_add_24),
                                    contentDescription = "Events"
                                )
                            })
                        NavigationBarItem(
                            selected = destination == "records",
                            onClick = { navController.navigate("records") },
                            icon = {
                                Icon(
                                    painterResource(R.drawable.baseline_fiber_smart_record_24),
                                    contentDescription = "Events"
                                )
                            })


                    }
                }
            }) {
                Column(modifier = Modifier
                    .padding(it)
                    .padding(10.dp)) {
                    NavHost(navController = navController, startDestination = "splash") {
                        composable("splash") {
                            SplashScreen(navController)
                        }
                        composable("events") {
                            EventsScreen(navController)
                        }
                        composable("tickets") {
                            TicketsScreen(navController)
                        }
                        composable("ticketDetails/{id}") {
                            TicketDetailsScreen(navController, it.arguments?.getString("id"))
                        }
                        composable("eventDetails/{id}") {
                            EventDetailsScreen(navController, it.arguments?.getString("id"))
                        }
                        composable("records") {
                            RecordsScreen(navController)
                        }
                        composable("ticketCreate") {
                            TicketCreateScreen(navController)
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
    WorldskillsA_AM_2Theme {
        Greeting("Android")
    }
}