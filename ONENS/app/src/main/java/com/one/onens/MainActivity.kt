package com.one.onens

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.one.onens.ui.theme.OnensTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnensTheme {
                val navController = rememberNavController()
                var modalState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
                val scope = rememberCoroutineScope()
                val desintation = navController.currentBackStackEntryAsState().value?.destination?.route
                ModalBottomSheetLayout(sheetContent ={
                                                     Column(
                                                         Modifier
                                                             .fillMaxWidth()
                                                             .background(Color.White)
                                                             .padding(10.dp)
                                                     ) {
                                                         Spacer(modifier = Modifier.height(60.dp))
                                                         Text(
                                                             text = "SERVICES",
                                                             fontWeight = FontWeight.Bold,
                                                             fontSize = 22.sp
                                                         )
                                                         Spacer(modifier = Modifier.height(14.dp))
                                                         Row(verticalAlignment = Alignment.CenterVertically) {
                                                             Box(modifier = Modifier.background(Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp )), contentAlignment = Alignment.Center) {
                                                                 Icon(
                                                                     imageVector = Icons.Default.Menu,
                                                                     contentDescription = "Menu",
                                                                     modifier = Modifier.padding(14.dp  )
                                                                 )
                                                             }
                                                             Spacer(modifier = Modifier.width(4.dp  ))
                                                             Column() {
                                                                 Text(text = "Feedback", fontWeight = FontWeight.Bold)
                                                                 Text(text = "Share your feedback to help us improveA")
                                                             }
                                                         }
                                                         Spacer(modifier = Modifier.height(10.dp))
                                                         Row(verticalAlignment = Alignment.CenterVertically) {
                                                             Box(modifier = Modifier.background(Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp )), contentAlignment = Alignment.Center) {
                                                                 Icon(
                                                                     painter = painterResource(R.drawable.baseline_school_24),
                                                                     contentDescription = "Menu",
                                                                     modifier = Modifier.padding(14.dp  )
                                                                 )
                                                             }
                                                             Spacer(modifier = Modifier.width(4.dp  ))
                                                             Column() {
                                                                 Text(text = "Scholarship/Sponsorship/Trg Award", fontWeight = FontWeight.Bold)
                                                                 Text(text = "Manage your scholarship/Spnosorship/Training award")
                                                             }
                                                         }
                                                         Spacer(modifier = Modifier.height(10.dp))
                                                         Row(verticalAlignment = Alignment.CenterVertically) {
                                                             Box(modifier = Modifier.background(Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp )), contentAlignment = Alignment.Center) {
                                                                 Icon(
                                                                     imageVector = Icons.Default.Menu,
                                                                     contentDescription = "Menu",
                                                                     modifier = Modifier.padding(14.dp  )
                                                                 )
                                                             }
                                                             Spacer(modifier = Modifier.width(4.dp  ))
                                                             Column() {
                                                                 Text(text = "G50 Submission", fontWeight = FontWeight.Bold)
                                                                 Text(text = "Submit personal infomration for security\nclearance purposes ")
                                                             }
                                                         }
                                                         Spacer(modifier = Modifier.height(500.dp))


                                                     }
                }, sheetState = modalState ) {
                    Scaffold(bottomBar = {

                        if(listOf("home", "life", "profile").contains(desintation)) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White), horizontalArrangement = Arrangement.SpaceAround) {
                                IconButton(onClick = { navController.navigate("home")}) {
                                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                                }
                                IconButton(onClick = {
                                    scope.launch {
                                        modalState.show()
                                    }
                                }) {
                                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Home")
                                }
                                IconButton(onClick = {
                                    navController.navigate("life")
                                   }) {
                                    Icon(painter = painterResource(id = R.drawable.baseline_wallet_24), contentDescription = "Home")
                                }
                                IconButton(onClick = { navController.navigate("profile") }) {
                                    Icon(imageVector = Icons.Default.Person,  contentDescription = "Home")
                                }

                            }
                        }
                    }) {
                        Column(Modifier.padding(it)) {
                            NavHost(navController = navController, startDestination = "splash") {
                                composable("splash") {
                                    SplashScreen(navController)
                                }
                                composable("home") {
                                    HomeScreen(navController)
                                }
                                composable("search") {
                                    SearchScreen(navController)
                                }
                                composable("form") {
                                    FormScreen(navController)
                                }
                                composable("settings") {
                                    SettingsScreen(navController)
                                }
                                composable("error") {
                                    ErrorScreen(navController)
                                }
                                composable("life") {
                                    LifeScreen(navController)
                                }
                                composable(
                                    "login"
                                )  {
                                    LoginScreen(navController = navController)
                                }
                                composable("inbox") {
                                    InboxScreen(navController = navController)
                                }
                                composable("profile") {
                                    ProfileScreen(navController = navController)
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
    OnensTheme {
        Greeting("Android")
    }
}