package com.example.worldskillssingapore

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.worldskillssingapore.ui.theme.RedColor
import com.example.worldskillssingapore.ui.theme.WorldskillsSingaporeTheme

class MainActivity : ComponentActivity() {
    var countdown: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            WorldskillsSingaporeTheme {
               // object: CountDownTimer()
              val navController = rememberNavController()
                val context = LocalContext.current
                val view = LocalView.current
                val bottomListRoutes = listOf("ColorMyDiet", "Add My Calories", "My Profile")
                var currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
                countdown = remember {object: CountDownTimer(60000L, 1000L) {
                    override fun onTick(p0: Long) {
                        println(p0)
                    }

                    override fun onFinish() {
                        if(currentDestination != "login") {
                            navController.navigate("login") {
                                this.popUpTo("login")
                            }
                            LoginSharedPreference(context).clearSession()
                        }

                    }

                } }

                LaunchedEffect(key1 = currentDestination) {
                    if(currentDestination != "login") {
                        countdown?.start()
                    } else {
                        countdown?.cancel()
                    }


                }

                LaunchedEffect(key1 = currentDestination) {
                    val window = (view.context as Activity).window
                    if(currentDestination == "login") {

                        window.statusBarColor = Color.White.toArgb()
                    } else  {
                        window.statusBarColor = RedColor.toArgb()
                    }
                }

                Scaffold(bottomBar = {
                    if(currentDestination != "login" ) {
                        NavigationBar {
                            bottomListRoutes.forEach {
                                BottomNavigationItem(

                                    selectedContentColor = RedColor,
                                    selected = currentDestination == it,
                                    onClick = {
                                              var destination = when(it) {
                                                  "ColorMyDiet" -> "colorMyDiet"
                                                  "Add My Calories" -> "addMyCalories"
                                                  "My Profile" -> "profile"
                                                  else -> ""
                                              }

                                        navController.navigate(destination)

                                    },
                                    icon = {

                                      when(it) {
                                            "ColorMyDiet" ->  {
                                               val color =  if(currentDestination == "colorMyDiet") RedColor else Color.DarkGray

                                                Icon(imageVector = Icons.Outlined.Home, contentDescription = "Outlined", tint = color)

                                            }
                                            "Add My Calories" -> {
                                                val color =  if(currentDestination == "addMyCalories") RedColor else Color.DarkGray
                                                Icon(painterResource(R.drawable.outline_description_24), contentDescription = null, tint = color)}
                                            "My Profile" ->  {
                                                val color =  if(currentDestination == "profile") RedColor else Color.DarkGray
                                                Icon(imageVector = Icons.Outlined.Person, contentDescription = null, tint = color)}
                                            else -> {}
                                        }






                                    }, label = {
                                        when(it) {
                                            "ColorMyDiet" ->  {
                                                val color =  if(currentDestination == "colorMyDiet") RedColor else Color.DarkGray

                                                Text(text = it, fontSize = 12.sp, color = color)
                                            }
                                            "Add My Calories" -> {
                                                val color =  if(currentDestination == "addMyCalories") RedColor else Color.DarkGray

                                                Text(text = it, fontSize = 12.sp, color = color)}
                                            "My Profile" ->  {
                                                val color =  if(currentDestination == "profile") RedColor else Color.DarkGray
                                                Text(text = it, fontSize = 12.sp, color = color)}
                                            else -> {}
                                        }


                                    })
                            }
                        }
                    }

                }) {
                    Column(modifier = Modifier.padding(it)) {
                        NavHost(navController = navController, startDestination = "login") {
                            composable("login") {
                                LoginScreen(navController)
                            }
                            composable("changePassword") {
                                ChangePasswordScreen(navController)
                            }
                            composable("profile") {
                                ProfileScreen(navController)
                            }
                            composable("addMyCalories") {
                                AddMyCaloriesScreen(navController = navController)
                            }
                            composable("colorMyDiet") {
                                ColorMyDietScreen(navController)
                            }
                            composable("foodCourt/{foodCourtId}") {
                                FoodCourtScreen(navController, it.arguments?.getString("foodCourtId"))
                            }
                            composable("stall/{stallId}") {
                                StallScreen(navController, it.arguments?.getString("stallId"))
                            }
                            composable("addFood/{foodId}/{calories}/{name}/{colorScheme}") {
                                it.arguments?.getString("calories")?.let { it1 ->
                                    it.arguments?.let { it2 ->
                                        it2.getString("colorScheme")?.let { it3 ->
                                            AddFoodScreen(navController, it.arguments?.getString("foodId"),
                                                it1, it.arguments!!.getString("name")!!, it3
                                            )
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

    override fun onUserInteraction() {
        if(countdown !=null) {
            countdown!!.cancel()
            countdown!!.start()
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
    WorldskillsSingaporeTheme {
        Greeting("Android")
    }
}