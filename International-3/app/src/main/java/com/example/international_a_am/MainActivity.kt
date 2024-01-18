package com.example.international_a_am

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.international_a_am.ui.theme.International_A_AMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navItems = remember {listOf("events", "tickets", "records")}
            var currentNavigation = navController.currentBackStackEntryAsState().value?.destination
            International_A_AMTheme {
                Scaffold(bottomBar= {
                    if(currentNavigation?.route.toString() != "splash") {
                        NavigationBar {
                            navItems.forEach {current ->
                                currentNavigation?.hierarchy?.any { it.route == current }
                                    ?.let { NavigationBarItem(selected = it, onClick = { navController.navigate(current)
                                                                                       println(current)
                                                                                       }, icon = { Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = "HJ"
                                    ) }, label = {
                                        Text(text = current.capitalize(), fontSize = 20.sp)

                                    }, colors = NavigationBarItemDefaults.colors(selectedTextColor = Color.Red))}
                            }
                        }
                    }
           }) {
                    Column(modifier = Modifier.padding(it   )) {
                        NavHost(navController, startDestination = "splash") {
                            composable("splash") {
                                SplashScreen(navController = navController)
                            }
                            navigation("eventsList", route = "events") {
                                composable("eventsList") {
                                    EventsListScreen(navController = navController)
                                }
                                composable("eventDetails/{id}") {
                                    it.arguments?.getString("id")?.toInt()
                                        ?.let { it1 -> EventDetailsScreen(navController = navController, id = it1) }
                                }
                            }
                            navigation("ticketsList", route = "tickets") {
                                composable("ticketsList") {
                                    TicketListScreen(navController = navController)
                                }
                                composable("createTicket") {
                                    CreateTicketScreen(navController = navController)
                                }
                                composable("ticketDetails/{id}") {
                                    it.arguments?.getString("id")?.toInt()
                                        ?.let { it1 -> TicketDetailsScreen(navController = navController, id = it1) }
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
    International_A_AMTheme {
        Greeting("Android")
    }
}