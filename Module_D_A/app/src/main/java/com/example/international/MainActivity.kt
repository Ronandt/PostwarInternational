package com.example.international

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.international.ui.theme.InternationalTheme
import com.example.international.ui.theme.Magenta
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InternationalTheme {
                val categories = remember {mutableStateListOf<Category>() }
                var drawerType by remember {mutableStateOf(false)}
                var shadow by remember {mutableStateOf(false)}
                var openDrawer by remember {mutableStateOf(false)}

                var selection by remember {mutableStateOf(-1)}
                val scope= rememberCoroutineScope()
                val context = LocalContext.current
                val jsonRetriever = JsonRetriever(context)
                var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                LaunchedEffect(Unit) {

                    categories.addAll(jsonRetriever.retrieve())

                }
                Box(modifier = Modifier.semantics {
                    this.testTagsAsResourceId = true
                }) {
                    ModalNavigationDrawer(drawerContent = {
                        if(!drawerType) {
                            Column(modifier = Modifier
                                .fillMaxHeight()
                                .width(300.dp)
                                .background(Color.White)) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(30.dp)
                                    .background(Color.Black), contentAlignment = Alignment.Center) {
                                    Text(text = "Skills", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                                }
                                Spacer(modifier =Modifier.height(20.dp))
                                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    items(categories.size) {
                                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                if (selection != it) {
                                                    selection = it
                                                } else {
                                                    selection = -1
                                                }

                                            }) {
                                            if(selection == it) {
                                                Image(Icons.Default.Close, contentDescription = "b")
                                            } else {
                                                Image(painter = painterResource(id = R.drawable.b), contentDescription = "b")
                                            }

                                            Text(text = categories[it].name, fontWeight = FontWeight.Bold)

                                        }
                                        if(selection == it) {
                                            categories[it].trades.forEach {
                                                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
                                                    navController.navigate("news/$it")
                                                }) {
                                                    Spacer(modifier = Modifier.width(14.dp))
                                                    Text(text = "-")
                                                    Text(text = it, fontWeight = FontWeight.Bold)

                                                }
                                            }

                                        }
                                    }
                                }
                                Spacer(Modifier.weight(1f))
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Gray)
                                    .clickable {
                                        navController.navigate("news")
                                    }) {
                                    Icon(Icons.Default.Search, contentDescription = "Content description")

                                    Column(modifier = Modifier.testTag("search").clickable {
                                        drawerType = true
                                    }) {
                                        Text(text = "Can't find what you want?")
                                        Text(text = "Search now", fontSize = 24.sp)

                                    }
                                }
                            }
                        } else {
                            SearchDrawer({drawerType =false}, navController = navController)
                        }

                    }, drawerState = drawerState) {
                        Scaffold(topBar = {
                            TopAppBar(title = { Text(text = "Worldskills") }, navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                    Icon(imageVector = Icons.Default.Menu , contentDescription =  "Menu", Modifier.testTag("menu"))
                                }
                            })
                        }) {
                            Column(modifier = Modifier
                                .fillMaxSize()
                                .padding(it)) {
                                NavHost(navController = navController, startDestination = "home" ) {
                                    composable("home") {
                                        HomeScreen(navController = navController)
                                    }
                                    composable("news/{title}?newsTitle={newsTitle}&skillName={skillName}&startDate={startDate}&endDate={endDate}", arguments = listOf(
                                        navArgument("newsTitle") {nullable = true}, navArgument("skillName") {nullable = true}, navArgument("startDate") {nullable = true}, navArgument("endDate") {nullable =true})) {
                                        it.arguments?.getString("title")?.let { it1 -> NewsScreen(
                                            navController = navController, title = it1, newsTitle = it.arguments!!.getString("newsTitle"), skillName = it.arguments!!.getString("skillName"), startDate = it.arguments!!.getString("startDate"), endDate = it.arguments!!.getString("endDate")
                                        ) { bool: Boolean -> shadow = bool }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(shadow) {
                        Column(modifier = Modifier.fillMaxSize().background(Color(0xAA000000))) {

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDrawer(onClose: () -> Unit, navController: NavController) {
    var searchTitle by remember {mutableStateOf("")}
    var skillName by remember {mutableStateOf("")}
    var startDate by remember {mutableStateOf("")}
    var endDate by remember {mutableStateOf("")}

    Column(modifier = Modifier
        .fillMaxHeight()
        .width(300.dp)
        .background(Magenta)
        .padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Search", fontWeight = FontWeight.SemiBold, )
            IconButton(onClick = { onClose()}) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close" )
            }


        }

        OutlinedTextField(value = searchTitle , onValueChange = {searchTitle = it}, modifier = Modifier.testTag("title").fillMaxWidth(), label = { Text(
            text = "Search title"
        )}, colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White))


        OutlinedTextField(value = skillName , onValueChange = {skillName = it}, modifier = Modifier.testTag("skill").fillMaxWidth(), label = { Text(
            text = "Skill Name"
        )}, colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.testTag("start").fillMaxWidth()) {

            OutlinedTextField(value = startDate, onValueChange = {startDate = it}, modifier = Modifier.width(100.dp), label = { Text(
                text = "Start Date",  fontSize = 13.sp
            )}, colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White))

            OutlinedTextField(value = endDate , onValueChange = {endDate = it}, modifier = Modifier.testTag("end").width(100.dp), label = { Text(
                text = "End date"
            , fontSize = 13.sp)}, colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White))
        }

        Spacer(modifier = Modifier.height(20.dp))
        val context = LocalContext.current
        IconButton(onClick = {
            val title = "Search Results"
            if(searchTitle.length > 10 || searchTitle.length < 1) {
                Toast.makeText(context, "Search must be longer than 1 and lesser than 10 chars", Toast.LENGTH_LONG).show()
                return@IconButton
            }
            if(skillName.length < 2 || skillName.length > 10) {
                Toast.makeText(context, "Search must be longer than 3 and lesser than 10 chars", Toast.LENGTH_LONG).show()
                return@IconButton
            }
            if(startDate == "" || endDate == "") {
                Toast.makeText(context, "Start date and end date must not be empty", Toast.LENGTH_LONG).show()
            }
            navController.navigate("news/${title}?newsTitle=${searchTitle}&skillName=${skillName}&startDate=$startDate&endDate=$endDate")

        }, colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White), modifier = Modifier.testTag("searchIcon")) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search" )
        }


    }

}