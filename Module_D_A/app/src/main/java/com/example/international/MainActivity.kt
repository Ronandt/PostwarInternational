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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
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
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.international.ui.theme.DarkBlue
import com.example.international.ui.theme.InternationalTheme
import com.example.international.ui.theme.Magenta
import com.example.international.ui.theme.Purple
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InternationalTheme {
                val categories = remember {mutableStateListOf<CategoryItem>() }
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
                    WorldSkillsAPI().getNewsInfo()?.let { categories.addAll(it) }
               //     categories.addAll(jsonRetriever.retrieve())

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
                                    .height(120.dp)
                                  ) {
                                    Image(painterResource(R.drawable.skills), contentDescription = null, modifier = Modifier.matchParentSize(), contentScale = ContentScale.Crop)

                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    item {
                                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 5.dp, start = 5.dp)
                                                .clickable {
                                                    navController.navigate("home")

                                                }) {

                                                    Image(painter = painterResource(id = R.drawable.b), contentDescription = "b", modifier = Modifier.size(30.dp))


                                                Text(text = "Home", fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.padding(5.dp))

                                            }
                                            Divider()


                                        }
                                    }
                                    items(categories.size) {
                                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 5.dp, start = 5.dp)
                                                .clickable {
                                                    if (selection != it) {
                                                        selection = it
                                                    } else {
                                                        selection = -1
                                                    }

                                                }) {
                                                if(selection == it) {
                                                    Image(Icons.Default.Close, contentDescription = "b", modifier = Modifier.size(23.dp))
                                                } else {
                                                    Image(painter = painterResource(id = R.drawable.box_2), contentDescription = "b", modifier = Modifier.size(22.dp))
                                                }

                                                Text(text = categories[it].category, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.padding(5.dp))

                                            }
                                            Divider()

                                            if(selection == it) {
                                                categories[it].subcategories.forEach {
                                                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                                        .clickable {
                                                            navController.navigate("news/${it.subcategory}")
                                                        }
                                                        .padding(10.dp)) {
                                                        Spacer(modifier = Modifier.width(14.dp))
                                                        Text(text = "-", fontSize = 12.sp)
                                                        Text(text = it.subcategory, fontWeight = FontWeight.Bold, fontSize = 12.sp)



                                                    }
                                                    Divider()
                                                }

                                            }
                                        }

                                    }
                                }
                                Spacer(Modifier.weight(1f))
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Purple)
                                    .clickable {
                                        drawerType = true
                                    }) {


                                    Column(modifier = Modifier
                                        .padding(10.dp)
                                        .testTag("search")
                                        .clickable {
                                            drawerType = true
                                        }) {

                                        Row {
                                            Icon(Icons.Default.Search, contentDescription = "Content description", tint = Color.White, modifier = Modifier
                                                .size(30.dp)
                                                .align(Alignment.Bottom))
                                            Column {
                                                Text(text = "Can't find you want?", color = Color.White, fontSize = 9.sp)
                                                Text(text = "Search now", fontSize = 20.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, color = Color.White)
                                            }

                                        }


                                    }
                                }
                            }
                        } else {
                            SearchDrawer({drawerType =false}, navController = navController)
                        }

                    }, drawerState = drawerState) {
                        Scaffold(topBar = {
                            CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = DarkBlue, titleContentColor = Color.White), title = { Image(
                                painter = painterResource(R.drawable.worldskills),
                                contentDescription = "Worldskills"
                            ) }, navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                    Icon(imageVector = Icons.Default.Menu , contentDescription =  "Menu", Modifier.testTag("menu"), tint = Color.White)
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
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xAA000000))) {

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
            Text(text = "Search", fontWeight = FontWeight.SemiBold, color = Color.White, fontStyle =  FontStyle.Italic)
            IconButton(onClick = { onClose()}) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint=  Color.White)
            }


        }

        OutlinedTextField(value = searchTitle , onValueChange = {searchTitle = it}, modifier = Modifier
            .testTag("title")
            .fillMaxWidth(), label = { Text(
            text = "News title", fontWeight = FontWeight.Bold, color = Color.Gray
        )}, colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White))


        OutlinedTextField(value = skillName , onValueChange = {skillName = it}, modifier = Modifier
            .testTag("skill")
            .fillMaxWidth(), label = { Text(
            text = "Skill Name", fontWeight = FontWeight.Bold, color = Color.Gray
        )}, colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
            .testTag("start")
            .fillMaxWidth()) {

            OutlinedTextField(value = startDate, onValueChange = {startDate = it}, modifier = Modifier.width(137.dp), label = { Text(
                buildAnnotatedString { append("Start date\n")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, fontSize = 13.sp)) {
                        append("(MM/dd/yyyy)")
                    }
                },  fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Gray, lineHeight = 13.sp
            )}, colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White))

            OutlinedTextField(value = endDate , onValueChange = {endDate = it}, modifier = Modifier
                .testTag("end")
                .width(137.dp), label = {

                Text(
                 buildAnnotatedString { append("End date\n")
                                      withStyle(style =  SpanStyle(fontWeight = FontWeight.Normal, fontSize = 13.sp, color = Color.Gray)) {
                                          append("(MM/dd/yyyy)")
                                      }
                                      }
            , fontSize = 15.sp,color = Color.Gray, fontWeight = FontWeight.Bold, lineHeight = 13.sp)}, colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White))
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
            if(!isValidDateFormat( startDate) || !isValidDateFormat(endDate)) {
                Toast.makeText(context, "Start and end date has to be dd/MM/yyyy", Toast.LENGTH_LONG).show()
            }
            navController.navigate("news/${title}?newsTitle=${searchTitle}&skillName=${skillName}&startDate=$startDate&endDate=$endDate")

        }, colors = IconButtonDefaults.iconButtonColors(), modifier = Modifier
            .testTag("searchIcon")
            .align(Alignment.End)) {
            Column(modifier = Modifier.size(40.dp).background(Color.White, shape = CircleShape), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search", modifier = Modifier.size(24.dp) )
            }

        }


    }

}
fun isValidDateFormat(dateString: String): Boolean {
    val regexPattern = Regex("\\d{2}/\\d{2}/\\d{4}")
    return regexPattern.matches(dateString)
}