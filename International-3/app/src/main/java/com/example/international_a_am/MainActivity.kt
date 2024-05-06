package com.example.international_a_am

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
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
    @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navItems = remember {listOf("events", "tickets", "records")}
            var currentNavigation = navController.currentBackStackEntryAsState().value?.destination
            var drawer = rememberDrawerState(initialValue = DrawerValue.Closed)
            LaunchedEffect(key1 = Unit ) {

                if(intent?.data != null) {
                    println(intent?.data)
                    navController.navigate(intent.data.toString())
                }
            }

            International_A_AMTheme {

                var bottomSheet = rememberModalBottomSheetState()

                Column(modifier= Modifier.semantics { testTagsAsResourceId = true }) {
                    ModalNavigationDrawer(
                        drawerContent = {
                            ModalDrawerSheet {

                            }
                        },
                        drawerState = drawer,
                    ) {

                        Scaffold(bottomBar= {
                            Text(text = "AAAA", modifier = Modifier.testTag("how"))
                            if(currentNavigation?.route.toString() != "splash") {
                                NavigationBar {
                                    navItems.forEach {current ->
                                        currentNavigation?.hierarchy?.any { it.route == current }
                                            ?.let { NavigationBarItem(selected = it, onClick = { navController.navigate(current)
                                                println(current)
                                            }, icon = {

                                                when(current) {
                                                    "events" -> {
                                                        Icon(
                                                            imageVector = Icons.Default.Home,
                                                            contentDescription = "events"
                                                        )
                                                    }
                                                    "tickets" -> {
                                                        Icon(

                                                            painterResource(id = R.drawable.baseline_post_add_24),
                                                            contentDescription = "tickets", modifier = Modifier
                                                                .semantics {
                                                                    testTagsAsResourceId = true
                                                                }
                                                                .testTag("tickets")
                                                        )
                                                    }
                                                    "records" -> {
                                                        Icon(
                                                            painterResource(id = R.drawable.baseline_fiber_smart_record_24),
                                                            contentDescription = "records"
                                                        )
                                                    }
                                                }
                                            }, label = {
                                                Text(text = current.capitalize(), fontSize = 20.sp)

                                            }, colors = NavigationBarItemDefaults.colors())}
                                    }
                                }
                            }
                        }) {
                            val observer = InternetObserver(context = applicationContext).observe().collectAsState(
                                initial = false
                            )
                            Column(modifier = Modifier
                                .padding(it)
                                .testTag("test")) {
                                NavHost(navController, startDestination = "splash") {


                                    composable("splash", enterTransition = {
                                        this.slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                                    }) {

                                        SplashScreen(navController = navController)
                                    }
                                    navigation("eventsList", route = "events") {
                                        composable("eventsList") {
                                            AnimatedContent(observer.value) {
                                                if(it) {
                                                    EventsListScreen(navController = navController)
                                                } else {
                                                    NoInternetScreen()
                                                }
                                            }


                                        }
                                        composable("eventDetails/{id}") { b->
                                            AnimatedContent(observer.value) {
                                                if(it) {
                                                    b.arguments?.getString("id")?.toInt()
                                                        ?.let { it1 -> EventDetailsScreen(navController = navController, id = it1) }
                                                } else {
                                                    NoInternetScreen()
                                                }
                                            }

                                        }
                                    }
                                    navigation("ticketsList", route = "tickets") {
                                        composable("ticketsList") {
                                            AnimatedContent(observer.value) {
                                                if(it) {
                                                    TicketListScreen(navController = navController)
                                                } else {
                                                    NoInternetScreen()
                                                }
                                            }

                                        }
                                        composable("createTicket") {
                                            AnimatedContent(observer.value) {
                                                if(it) {
                                                    CreateTicketScreen(navController = navController)
                                                } else {
                                                    NoInternetScreen()
                                                }
                                            }

                                        }
                                        composable("ticketDetails/{id}") {
                                            AnimatedContent(observer.value) { b->
                                                if(b) {
                                                    it.arguments?.getString("id")
                                                        ?.let { it1 -> TicketDetailsScreen(navController = navController, id = it1) }
                                                } else {
                                                    NoInternetScreen()
                                                }
                                            }

                                        }
                                    }
                                    composable("records") {
                                        AnimatedContent(observer.value) { b->
                                            if(b) {
                                                RecordsScreen(navController = navController)
                                            } else {
                                                NoInternetScreen()
                                            }
                                        }
                                    }
                                }
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