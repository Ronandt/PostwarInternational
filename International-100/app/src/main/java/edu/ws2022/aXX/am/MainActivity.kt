package edu.ws2022.aXX.am

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.ws2022.aXX.am.ui.theme.WorldskillsMod1Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorldskillsMod1Theme {
                val context = LocalContext.current
                val database = remember {AppDatabase.getDb(context)}
                val jsonextractor = remember {JsonExtractor(context)}
//Preload the information
                LaunchedEffect(Unit) {
                    //preload th data

                   val dao =  database.eventDao()
                    val transform = mutableListOf<Event>()

                    if(dao.selectAll().isEmpty()) {
                        jsonextractor.extractEvents().forEach {
                            transform.add(Event(it.image, it.text, it.title, it.viewCount, it.otherImages[0], it.otherImages[1], it.otherImages[2]))
                        }
                        dao.insertAll(transform)
                    }
                }
                val navController = rememberNavController()
                val currentDesintation = navController.currentBackStackEntryAsState().value?.destination?.route
                Scaffold(bottomBar = {
                    if(currentDesintation != "splash") {
                        NavigationBar() {
                            NavigationBarItem(selected = listOf("events","eventDetails" ).contains(currentDesintation), onClick = { navController.navigate("events")}, label = {
                                Text(text = "Home")
                            }, icon = {
                                Icon(painter = painterResource(id = R.drawable.baseline_home_24), contentDescription = "home" )
                            })
                            NavigationBarItem(selected =  listOf("tickets", "ticketCreate", "ticketDetails" ).contains(currentDesintation), onClick = { navController.navigate("tickets") }, label = {
                                Text(text = "Tickets")
                            }, icon = {
                                Icon(painter = painterResource(id = R.drawable.baseline_airplane_ticket_24), contentDescription = "home" )
                            })
                            NavigationBarItem(selected =  listOf("recorder" ).contains(currentDesintation), onClick = {navController.navigate("recorder") }, label = {
                                Text(text = "Records")
                            }, icon = {
                                Icon(painter = painterResource(id = R.drawable.baseline_fiber_smart_record_24), contentDescription = "home" )
                            })
                        }
                    }
                }) {
                    Column(modifier = Modifier.padding(it)) {
                        NavHost(navController = navController, startDestination =  "splash") {
                            composable("events") {
                                EventScreen(navController)
                            }
                            composable("tickets") {
                                TicketsScreen(navController)
                            }
                            composable("ticketCreate") {
                                TicketCreateScreen(navController)
                            }
                            composable("ticketDetails/{id}") {
                                TicketDetailsScreen(navController, id = it.arguments?.getString("id")?.toInt())
                            }
                            composable("recorder") {
                                RecorderScreen(navController)
                            }
                            composable(
                                "splash"
                            ) {
                                SplashScreen(navController = navController)
                            }
                            composable(

                                "eventDetails/{id}"
                            ) {
                                EventDetailsScreen(navController = navController, id = it.arguments?.getString("id")?.toInt())
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
    WorldskillsMod1Theme {
        Greeting("Android")
    }
}