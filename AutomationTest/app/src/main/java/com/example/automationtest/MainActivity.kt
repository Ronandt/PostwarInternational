package com.example.automationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.automationtest.ui.theme.AutomationTestTheme
import kotlinx.coroutines.launch


enum class DrawerMode {
    Search,
    Menu
}
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutomationTestTheme {

                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                val context = LocalContext.current
                val drawerController = rememberDrawerState(initialValue = DrawerValue.Closed)
LaunchedEffect(key1 = Unit) {
    ExtractJsonBro(context).getNews()
}
                ModalNavigationDrawer(drawerContent = {

                                                      ModalDrawerSheet {
                                                          var mode by remember {mutableStateOf<DrawerMode>(DrawerMode.Menu)}
                                                          var selected by remember {mutableStateOf<String?>(null)}
                                                          if(mode == DrawerMode.Menu) {
                                                              listOf("Whatever and technology", "construction").forEach {

                                                                  Spacer(modifier = Modifier.height(16.dp))
                                                                  NavigationDrawerItem(
                                                                      shape = RoundedCornerShape(1.dp),
                                                                      label = {
                                                                          Row(horizontalArrangement = Arrangement.spacedBy(5.dp )) {
                                                                              if(selected == it) Icon(
                                                                                  Icons.Default.Clear,
                                                                                  contentDescription = "Close"
                                                                              ) else {
                                                                                  Icon(
                                                                                      imageVector = Icons.Default.Add,
                                                                                      contentDescription = "Expand"
                                                                                  )
                                                                              }
                                                                              Text("$it   ")
                                                                          }
                                                                      },
                                                                      selected = true ,
                                                                      onClick = {
                                                                          if(selected == it) {
                                                                              selected = null
                                                                          } else {
                                                                              selected = it
                                                                          }
                                                                      })

                                                                  if(selected == it) {
                                                                      listOf("sub1", "sub2", "Sub3").forEach {
                                                                          NavigationDrawerItem(
                                                                              label = { Row {
                                                                                  Spacer(
                                                                                      modifier = Modifier.width(
                                                                                          10.dp
                                                                                      )
                                                                                  )
                                                                                  Text(text = "$it")
                                                                              } },
                                                                              selected = false,
                                                                              onClick = {  navController.navigate("search")})
                                                                      }
                                                                  }

                                                              }
                                                              Spacer(modifier = Modifier.weight(1f))
                                                              Row(modifier = Modifier
                                                                  .fillMaxWidth()
                                                                  .background(
                                                                      Color.Black
                                                                  )
                                                                  .clickable {
                                                                      mode = DrawerMode.Search
                                                                  }, verticalAlignment = Alignment.CenterVertically) {
                                                                  Icon(
                                                                      Icons.Default.Search,
                                                                      contentDescription = "Search now", tint = Color.White
                                                                  )

                                                                  Text(text = "Search now", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, modifier = Modifier.padding(vertical = 20.dp   ))
                                                              }
                                                          } else {
                                                              var title by remember {mutableStateOf("")}
                                                              var skillName by remember {mutableStateOf("")}
                                                              var startDate by remember { mutableStateOf("")}
                                                              var endDate by remember {mutableStateOf("")}
                                                              Column(modifier = Modifier
                                                                  .fillMaxWidth()
                                                                  .padding(10.dp)) {
                                                                  Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
                                                                      Text(text = "Search")
                                                                      IconButton(onClick = { mode = DrawerMode.Menu }) {
                                                                          Icon(Icons.Default.Clear, contentDescription = null)
                                                                      }
                                                                  }

                                                                  Spacer(
                                                                      modifier = Modifier.height(
                                                                          18.dp
                                                                      )
                                                                  )

                                                                  Column(verticalArrangement = Arrangement.spacedBy(14.dp   )) {

                                                                      TextField(
                                                                          value = title,
                                                                          onValueChange = {title = it},
                                                                          modifier = Modifier.fillMaxWidth(),
                                                                          placeholder = { Text(text = "News Title", color = Color.Gray)}
                                                                      )
                                                                      TextField(
                                                                          value = title,
                                                                          onValueChange = {title = it},
                                                                          modifier = Modifier.fillMaxWidth(),
                                                                          placeholder = { Text(text = "Skill Name", color = Color.Gray)}
                                                                      )
                                                                      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                                                          TextField(
                                                                              value = title,
                                                                              onValueChange = {title = it},
                                                                              modifier = Modifier.width(150.dp  ),
                                                                              placeholder = { Text(text = "Start Date\nMM/dd/yyyy", color = Color.Gray)}
                                                                          )
                                                                          TextField(
                                                                              value = title,
                                                                              onValueChange = {title = it},
                                                                              modifier = Modifier.width(150.dp  ),
                                                                              placeholder = { Text(text = "End Date\nMM/dd/yyyy", color = Color.Gray)}
                                                                          )
                                                                      }

                                                                  }

                                                                  Spacer(
                                                                      modifier = Modifier.height(
                                                                          40.dp
                                                                      )
                                                                  )

                                                                  Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                                                      FilledIconButton(onClick = { /*TODO*/ },  shape = CircleShape,colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White)) {
                                                                          Icon(
                                                                              imageVector = Icons.Default.Search,
                                                                              contentDescription = "Search"
                                                                          )
                                                                      }
                                                                  }

                                                              }
                                                          }


                                                      }
                }, drawerState = drawerController) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = {
                                Text(text = "Worldskills Home")
                            }, navigationIcon = {

                                IconButton(onClick = {
                                    scope.launch {
                                        drawerController.open()
                                    }
                                 }) {
                                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu" )
                                }
                            })
                        }
                    ) {

                        Column(modifier = Modifier.padding(it)) {
                            NavHost(navController = navController, startDestination = "main" ) {
                                composable("main") {
                                    MainScreen(navController)
                                }

                                composable("newsList?search={search}", arguments = listOf(navArgument("search") {nullable = true})) {
                                    SearchScreen(navController, it.arguments?.getString("search"))
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
    AutomationTestTheme {
        Greeting("Android")
    }
}