package com.example.pocketvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pocketvault.ui.theme.LightGreen
import com.example.pocketvault.ui.theme.PocketvaultTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketvaultTheme {

                val navController = rememberNavController()
                val destination = navController.currentBackStackEntryAsState().value?.destination?.route
                Scaffold(
                    bottomBar = {
                        if(destination == "settings" || destination == "passwords") {
                            NavigationBar(containerColor =  Color.Black) {
                                listOf( "passwords", "settings").forEach {
                                    NavigationBarItem(selected = destination == it, onClick = {navController.navigate(it) }, icon = {
                                        when(it) {
                                            "settings" -> {
                                                Icon(painter = painterResource(R.drawable.baseline_menu_24), contentDescription ="settings")
                                            }

                                            "passwords" ->{
                                                Icon(painter = painterResource(R.drawable.baseline_grid_view_24), contentDescription = "passwords")
                                            }
                                        }

                                    }, label = {
                                               Text(text = it.capitalize(Locale.ENGLISH))
                                    }, colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Black , unselectedIconColor = Color.LightGray, unselectedTextColor = Color.LightGray, selectedIconColor = LightGreen, selectedTextColor = LightGreen))
                                }

                            }
                        }

                    }
                ) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .background(Color.Black).padding(horizontal = 8.dp    )) {

                        NavHost(navController = navController, startDestination = "login") {
                            composable("login") {
                                LoginScreen(navController)
                            }
                            composable("register") {
                                RegisterScreen(navController)
                            }
                            composable("passwords") {
                                PasswordsScreen(navController)
                            }
                            composable("passwordDetail/{id}") {
                                PasswordDetailScreen(navController, it.arguments?.getString("id"))
                            }
                            composable("settings") {
                                SettingsScreen(navController)
                            }
                            composable("createPassword") {
                                CreatePasswordScreen(navController)
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
    PocketvaultTheme {
        Greeting("Android")
    }
}