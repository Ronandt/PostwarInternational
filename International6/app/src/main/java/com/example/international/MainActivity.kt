package com.example.international

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.international.ui.theme.DarkBlue
import com.example.international.ui.theme.InternationalTheme
import com.example.international.ui.theme.Purple
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
           // var sideNav by remember {mutableStateOf(false)}
            var sideNavS = rememberDrawerState(initialValue =DrawerValue.Closed )
            val navController = rememberNavController()
            val context = LocalContext.current
            val categories = remember {
                JsonRetriever(context)
                    .retrieveCategory()}
            ModalNavigationDrawer(drawerContent = {
                var searchMode by remember {mutableStateOf(false)}
                var expanded by remember {mutableStateOf(-1)}

                Column(modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .background(Color.White), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    if(searchMode) {
                        SearchDialog(navController = navController, {searchMode = false})
                    } else {
                        repeat(categories?.size!!) { catSize ->

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (expanded != catSize) {
                                        expanded = catSize
                                    } else {
                                        expanded = -1
                                    }

                                }){
                                Icon(imageVector = if(expanded!= catSize) Icons.Default.AccountBox else Icons.Default.Close, contentDescription = "")
                                Text(text = categories[catSize].category, fontWeight = FontWeight.Bold)


                            }
                            Divider()
                            AnimatedVisibility(visible = expanded == catSize) {
                                Column {

                                    categories[catSize]?.skills?.size?.let { it1 ->
                                        repeat(it1) {
                                            Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp)
                                                .clickable {
                                                    navController.navigate("search/${catSize}/${it}")
                                                    scope.launch {
                                                        sideNavS.close()
                                                    }

                                                }) {
                                                println(it)
                                                println(it1)
                                                Text(text = "- ${categories[catSize].skills[it].name}", fontWeight = FontWeight.Bold, modifier = Modifier.offset(x = 14.dp))
                                            }
                                            Divider()
                                        }
                                    }

                                }




                            }


                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Purple)
                            .clickable {
                                searchMode = true
                            }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint =  Color.White, modifier = Modifier.size(40.dp))
                            Text(text = "Search now", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }

                }


            }, drawerState = sideNavS) {
                Scaffold(topBar = {
                    TopAppBar(backgroundColor = DarkBlue) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            IconButton(onClick = {
                                scope.launch {
                                    sideNavS.open()
                                }
                                }) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = Color.White, modifier = Modifier.align(
                                    Alignment.CenterStart))
                            }

                            Image(painter = painterResource(R.drawable.worldskills), contentDescription = "Worldskills", contentScale = ContentScale.Crop, modifier = Modifier
                                .align(Alignment.Center)
                                .size(70.dp, 50.dp))

                        }
                    }
                }) {
                    Column(modifier = Modifier.padding(it)) {
                        NavHost(navController = navController, startDestination = "main") {
                            composable("main") {
                                MainScreen(navController)
                            }
                            composable("search/{cat}/{id}") {
                                it.arguments?.getString("id")?.toInt()
                                    ?.let { it1 -> it.arguments!!.getString("cat")
                                        ?.let { it2 -> SearchScreen(navController, it1, it2.toInt()) } }
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
    InternationalTheme {
        Greeting("Android")
    }
}

@Composable
fun SearchDialog(navController: NavController, onClose: () -> Unit) {
    val context = LocalContext.current
    var newsTitle by remember {mutableStateOf("")}
    var skillName by remember {mutableStateOf("")}
    var startDate by remember {mutableStateOf("")}
    var endDate by remember {mutableStateOf("")}
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Purple), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Search", color = Color.White)
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, tint = Color.White, contentDescription = "Close")
            }

        }
        OutlinedTextField(value = newsTitle, onValueChange =  {newsTitle = it}, label = { Text(text = "News Title")}, colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = Color.White))
        OutlinedTextField(value = skillName, onValueChange =  {skillName = it}, label = { Text(text = "Skill name")}, colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = Color.White))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            OutlinedTextField(value = startDate, onValueChange =  {startDate = it}, label = { Text(text = "Start Date")}, modifier = Modifier.fillMaxWidth(0.3f), colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = Color.White))
            OutlinedTextField(value = endDate, onValueChange =  {endDate = it}, label = { Text(text = "End Date")}, modifier = Modifier.fillMaxWidth(0.3f), colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = Color.White))
        }
        IconButton(onClick = {
            if(newsTitle.length > 10 || newsTitle.length  < 1) {
                Toast.makeText(context, "News title has to be between 1 and 10", Toast.LENGTH_LONG).show()
                return@IconButton
            }
            if(skillName.length > 10 || skillName.length < 3) {
                Toast.makeText(context, "Skill name has to be between 3 and 10 ", Toast.LENGTH_LONG).show()
                return@IconButton
            }
            try {
                var formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val date = formatter.parse(startDate)
                val date2 = formatter.parse(endDate)
            } catch(e: Exception) {
                Toast.makeText(context, "Start date or end date has to be a valid date", Toast.LENGTH_LONG).show()
                return@IconButton
        }



            navController.navigate("search")}, modifier = Modifier.background(color = Color.White, shape = CircleShape)) {
            Icon(Icons.Default.Search, tint = Color.Black, contentDescription = "Search")
        }

    }
}