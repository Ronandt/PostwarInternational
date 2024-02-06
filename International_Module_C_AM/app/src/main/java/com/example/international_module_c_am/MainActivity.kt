package com.example.international_module_c_am

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.international_module_c_am.ui.theme.DarkGray
import com.example.international_module_c_am.ui.theme.International_Module_C_AMTheme
import com.example.international_module_c_am.ui.theme.LightBlue
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val view = LocalView.current
            val context  = LocalContext.current
            val sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("darkMode", false)
            LaunchedEffect(Unit){
                navController.currentBackStackEntryFlow.collectLatest {

                    println(it.destination.route)
                    val window = (view.context as Activity).window
                    when(it.destination.route) {
                        "resultDetail/{id}" -> {

                            window.statusBarColor = if(sharedPref) DarkGray.toArgb() else Color.White.toArgb()
                        }
                        "newsDetail/{id}" -> {
                            window.statusBarColor = if(sharedPref) DarkGray.toArgb() else Color.White.toArgb()

                        }
                        "home" -> {
                            window.statusBarColor = LightBlue.toArgb()
                        }
                    }
                }
            }



            International_Module_C_AMTheme {
                // A surface container using the 'background' color from the theme
                NavHost(navController, startDestination ="home") {
                    composable("home") {
                        HomeScreen(navController)
                    }
                    composable("newsDetail/{id}") {
                        it.arguments?.getString("id")?.toInt()
                            ?.let { it1 -> NewsDetailScreen(navController, it1) }
                    }
                    composable("resultDetail/{id}") {
                        it.arguments?.getString("id")?.toInt()
                            ?.let { it1 -> ResultDetailScreen(navController, it1) }
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
    International_Module_C_AMTheme {
        Greeting("Android")
    }
}