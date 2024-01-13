package com.example.international

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.international.ui.theme.InternationalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val view = LocalView.current

            InternationalTheme {
                if(navController.currentBackStackEntryAsState().value?.destination?.route in listOf("newsDetail", "resultDetail")) {
                    val window = (view.context as Activity).window
                    window.statusBarColor = Color.White.toArgb()
                } else {
                    val window = (view.context as Activity).window
                    window.statusBarColor = Color(0xFF335F82).toArgb()
                }
                NavHost(navController = navController,startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController = navController)
                    }
                    composable("newsDetail") {
                        NewsDetailScreen(navController = navController)
                    }
                    composable("resultDetail") {
                        ResultDetailScreen(navController = navController)
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
    InternationalTheme {
        Greeting("Android")
    }
}