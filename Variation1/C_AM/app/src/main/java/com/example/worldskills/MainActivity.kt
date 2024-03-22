package com.example.worldskills

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.worldskills.ui.theme.WorldskillsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            WorldskillsTheme {
                var permission = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {

                }

                LaunchedEffect(Unit) {
                    permission.launch(android.Manifest.permission.POST_NOTIFICATIONS)

                    //create the channel
                    val importance = NotificationManager.IMPORTANCE_DEFAULT
                    val channel = NotificationChannel("Download", "Download", importance).apply {
                        description = "Download"
                    }

                    //get the notification maanger
                    val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    if(notificationManager != null) {
                        //crate the notification channel with the notifcation manager
                        notificationManager.createNotificationChannel(channel)

                    }

                    //build the notification manager
                    val b = NotificationCompat.Builder(applicationContext, "Download").setContentTitle("Download").setSmallIcon(R.drawable.worldskills)
                    notificationManager.notify((0..1000).random(),b.build() )
                }
                val navController = rememberNavController()
                var destination = navController.currentBackStackEntryAsState().value?.destination?.route
                LaunchedEffect(Unit) {
                    if(intent.data !=null) {
                        navController.navigate(intent.data!!)
                    }
                }
                Scaffold(bottomBar = {
                    if(destination != "splash") {
                        NavigationBar {
                            NavigationBarItem(
                                selected = destination == "events" || destination == "eventDetails",
                                onClick = {navController.navigate("events")},
                                icon = { Icon(painterResource(R.drawable.baseline_home_24), contentDescription = "events") })

                            NavigationBarItem(
                                selected = destination == "tickets" || destination == "ticketCreate" || destination == "ticketDetails",
                                onClick = {navController.navigate("tickets")},
                                icon = { Icon(painterResource(R.drawable.baseline_post_add_24), contentDescription = "tickets") })

                            NavigationBarItem(
                                selected = destination == "records",
                                onClick = {navController.navigate("records")},
                                icon = { Icon(painterResource(R.drawable.baseline_fiber_smart_record_24), contentDescription = "Record") })
                        }
                    }

                }) {
                    Column(modifier = Modifier.padding(it)) {
                        NavHost(navController = navController, startDestination = "splash") {
                            composable("splash") {
                                SplashScreen(navController)
                            }
                            composable("events") {
                                EventsScreen(navController)
                            }
                            composable("eventDetails/{id}") {
                                it.arguments?.getString("id")
                                    ?.let { it1 -> EventDetailsScreen(navController, it1) }
                            }
                            composable("tickets") {
                                TicketsScreen(navController)
                            }
                            composable("ticketCreate") {
                                TicketCreateScreen(navController)
                            }
                            composable("ticketDetails/{id}") {
                                it.arguments?.getString("id")
                                    ?.let { it1 -> TicketDetailsScreen(navController, it1) }
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
    WorldskillsTheme {
        Greeting("Android")
    }
}