package com.example.worldskills_c_am

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.worldskills_c_am.ui.theme.DarkGray
import com.example.worldskills_c_am.ui.theme.Worldskills_c_amTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Components:
        /*
        1. Standard Card
        2. Column scaffold with standard padding
        3. Blue and bold header (For settings hader, Card headers/subheaders [numbers] ,history evennt name, and detail headers )
        3. Subtitles (For card subtitle and mock data text)
        4. Black and bold texxt for history header text, results header text, news detail summary text
        5. Try to do flows and shared observers


         */

        setContent {
            var navController = rememberNavController()
            var currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
            val navigationList = listOf("home", "communities", "create", "chat", "inbox")
            var dropdownOptions = listOf("Home", "blah")
            Worldskills_c_amTheme {

                Scaffold(topBar = {
                    if(currentDestination != "create") {
                        TopAppBar(backgroundColor = Color.Black) {

                            Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                Row(verticalAlignment = Alignment.CenterVertically){
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(painterResource(id = R.drawable.hamburger_icon), contentDescription = "Hambureder", modifier = Modifier.size(35.dp   ), tint = Color.White)

                                    }
                                    Spacer(modifier = Modifier.width(6.dp))
                                    if(currentDestination == "home") {
                                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp ), modifier = Modifier

                                            .background(
                                                DarkGray,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .padding(6.dp)) {
                                            Text(
                                                text = "Home",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 20.sp,
                                                color = Color.White
                                            )
                                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Down", tint = Color.White)

                                        }
                                    }

                                    else {
                                        currentDestination?.capitalize()?.let {
                                            Text(
                                                text = it,
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 16.sp,
                                                color = Color.White
                                            )
                                        }
                                    }

                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(Icons.Default.Search, contentDescription = "Defualt Search", modifier = Modifier.size(30.dp  ), tint  = Color.White)
                                    }
                                    Box {
                                        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription ="color", modifier = Modifier
                                            .size(50.dp)
                                            .clip(
                                                CircleShape
                                            ), contentScale = ContentScale.Crop)
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Spacer(modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .size(12.dp)
                                            .background(Color.Green, shape = CircleShape))
                                    }
                                }
                            }
                        }
                    }



                },bottomBar = {
                    NavigationBar(containerColor = Color.Black, contentColor = Color.White) {
                        navigationList.forEach {
                            var color = if(currentDestination == it) Color.White else Color.Gray

                            NavigationBarItem(
                                selected = it == currentDestination,
                                onClick = { navController.navigate(it) },
                                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Black),
                                icon = {

                                    when(it) {

                                        "home" -> {
                                            if(currentDestination == it) {
                                                Icon(Icons.Filled.Home, contentDescription = "Home", tint =  color  )

                                            } else {
                                                Icon(Icons.Outlined.Home, contentDescription = "Home", tint = color  )

                                            }

                                        }
                                        "communities" -> {
                                            if(currentDestination == it) {
                                                Icon(painter = painterResource(R.drawable.baseline_people_outline_24), contentDescription = "People" ,tint =  color  )
                                            } else {
                                                Icon(painter = painterResource(R.drawable.baseline_people_outline_24), contentDescription = "People", tint = color   )
                                            }


                                        }
                                        "create" -> {
                                            if(currentDestination == it) {
                                                Icon(Icons.Default.Add, contentDescription = "Add",  tint = color  )
                                            } else {
                                                Icon(Icons.Default.Add, contentDescription = "Add", tint =  color  )
                                            }


                                        }
                                        "chat" -> {
                                            if(currentDestination == it) {
                                                Icon(painter = painterResource(id = R.drawable.outline_3p_24), contentDescription = "Chat", tint =  color   )
                                            } else {
                                                Icon(painter = painterResource(id = R.drawable.outline_3p_24), contentDescription = "Chat" , tint =  color  )
                                            }


                                        }
                                        "inbox" -> {
                                            if(currentDestination == it) {
                                                Icon(Icons.Filled.Notifications, contentDescription = "Chat" , tint = color)
                                            } else {
                                                Icon(Icons.Outlined.Notifications, contentDescription = "Chat" , tint = color)
                                            }

                                        }
                                    }

                                }, label = { Text(text = it.capitalize(), color = color, maxLines = 1, overflow = TextOverflow.Ellipsis)})
                        }

                    }
                }) {
                    Column(modifier = Modifier.background(Color.Black).padding(it)) {
                        Spacer(modifier = Modifier.height(14.dp))
                        NavHost(navController = navController, startDestination= "home" ) {
                            composable("home") {
                                HomeScreen(navController)
                            }
                            composable("communities") {
                                CommunitiesScreen(navController)
                            }
                            composable("create") {
                                CreateScreen(navController)
                            }

                            composable("chat") {
                                ChatScreen(navController)
                            }
                            composable("inbox") {
                                InboxScreen(navController)
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
    Worldskills_c_amTheme {
        Greeting("Android")
    }
}