package com.example.shoepanic

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoepanic.ui.theme.ShoePanicTheme
//THIS SESSION WILL BE UI ONLAY DUE TO THE SCOPE OF FEATURES LOL
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            ShoePanicTheme {
                NavHost(navController, startDestination ="login") {
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("changePassword") {
                        ChangePasswordScreen(navController)
                    }
                    composable("feedback") {
                        FeedbackScreen(navController)
                    }
                    composable("resetPassword") {
                        ResetPasswordScreen(navController)
                    }
                    composable("customerHome") {
                        CustomerHomeScreen(navController)
                    }
                    composable("physicalVerified") {
                        PhysicalVerifiedScreen(navController)
                    }
                    composable("scan/{type}") {
                        ScanScreen(navController)
                    }
                    composable("staffHome") {
                        StaffHomeScreen(navController)

                    }
                    composable("queue") {
                        QueueScreen(navController)
                    }
                    composable("specialQueue") {
                        SpecialQueueScreen(navController = navController)
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
    ShoePanicTheme {
        Greeting("Android")
    }
}