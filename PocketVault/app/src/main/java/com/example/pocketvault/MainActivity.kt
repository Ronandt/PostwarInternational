package com.example.pocketvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pocketvault.ui.theme.LightGreen
import com.example.pocketvault.ui.theme.PocketVaultTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketVaultTheme {
                val navController = rememberNavController()

                var currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
                Scaffold(bottomBar = {
                    if(currentDestination == "settings" || currentDestination == "passwords")
                                     NavigationBar(containerColor = Color.Black) {
                                         NavigationBarItem(
                                             selected = currentDestination == "passwords"  ,
                                             onClick = { navController.navigate("passwords")},
                                             icon = { Icon(painterResource(id = R.drawable.baseline_space_dashboard_24), contentDescription ="Dashboard", tint = if(currentDestination == "passwords") LightGreen else Color.LightGray)}, label = {
                                                 Text(text = "Passwords", color = if(currentDestination == "passwords") LightGreen else Color.LightGray)

                                             }, colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent))
                                         NavigationBarItem(
                                             selected = currentDestination == "settings"  ,
                                             onClick = { navController.navigate("settings") },
                                             icon = { Icon(painterResource(id = R.drawable.baseline_toc_24), contentDescription ="settings", tint = if(currentDestination == "settings") LightGreen else Color.LightGray)}, label = {
                                                 Text(text = "Settings", color = if(currentDestination == "settings") LightGreen else Color.LightGray)

                                             }, colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent))
                                     }

                },
                    floatingActionButton = {
                        if(currentDestination == "passwords" ) {
                            FloatingActionButton(onClick = { navController.navigate("createPassword")}, containerColor = LightGreen, shape = CircleShape) {
                                Icon(imageVector = Icons.Default.Add, contentDescription ="Add", tint = Color.White)
                            }
                        }

                    }
                    ){
                    Column(modifier = Modifier
                        .padding(it)
                        .fillMaxWidth()
                        .background(Color.Black).semantics { testTagsAsResourceId = true     }){
                        NavHost(navController = navController, startDestination = "login" ) {
                            composable("login") {
                                LoginScreen(navController)
                            }
                            composable("register") {
                                RegisterScreen(navController)
                            }
                            composable("passwords") {
                                PasswordsScreen(navController)
                            }
                            composable("createPassword") {
                                CreatePasswordScreen(navController)
                            }
                            composable("settings") {
                                SettingsScreen(navController)
                            }
                            composable("edit/{id}") {
                                it.arguments?.getString("id")?.toInt()
                                    ?.let { it1 -> EditScreen(navController, it1) }
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
    PocketVaultTheme {
        Greeting("Android")
    }
}