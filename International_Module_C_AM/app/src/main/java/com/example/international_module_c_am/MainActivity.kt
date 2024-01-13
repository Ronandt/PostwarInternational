package com.example.international_module_c_am

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.international_module_c_am.ui.theme.International_Module_C_AMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
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