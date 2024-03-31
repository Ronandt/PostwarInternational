package com.example.worldskills

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.worldskills.ui.theme.WorldskillsTheme

class MainActivity : ComponentActivity() {


    //Components:
    /*
    1. Standard Card
    2. Column scaffold with standard padding
    3. Blue and bold header (For settings hader, Card headers/subheaders [numbers] ,history evennt name, and detail headers )
    3. Subtitles (For card subtitle and mock data text)
    4. Black and bold texxt for history header text, results header text, news detail summary text
    5. Try to do flows and shared observers


     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorldskillsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")

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