package com.example.pocketvault

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pocketvault.ui.theme.DarkGreen
import com.example.pocketvault.ui.theme.LightGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun PasswordsScreen(navController: NavController) {

    var sheetState = rememberModalBottomSheetState(initialValue =ModalBottomSheetValue.Hidden )
    var confirmation by remember { mutableStateOf(false )}
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val database = AppDatabase.getDb(context)
    var dataInformation = remember { mutableStateListOf<Note>()}
    val noteJson = database.noteDao()
    val loginManager = LoginManager(context).getSession()
    var selectedOne by remember {mutableStateOf(-1)}
    var change by remember {mutableStateOf(false)}
    LaunchedEffect(change   ) {
        dataInformation.clear()
        val json = JsonExtractor(context = context).extractResource()
        val jsonTransformed = mutableListOf<Note>()
        json.forEach {
            jsonTransformed.add(Note(password =it.password, userKey = loginManager, title = it.title, website = it.website, username = it.username ))
        }
        val data = database.noteDao().getAll().filter {
            it.userKey == loginManager
        }
        if(data.isEmpty()) {
            noteJson.insertAll(jsonTransformed)
            dataInformation.addAll(noteJson.getAll().filter {it.userKey == loginManager})
        } else {
           dataInformation.addAll(data)
        }

    }
        ModalBottomSheetLayout(sheetState = sheetState ,sheetContent = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)) {
                if(confirmation) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)) {
                        Text(
                            text = "Are you sure",
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                        IconButton(onClick = {
                            scope.launch {

                                confirmation = false
                                sheetState.hide()

                            }

                        }) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                    ApplicationCard(onClick = {
                        scope.launch {
                            database.noteDao().delete(database.noteDao().getByRealId(selectedOne))
                            change = !change
                            confirmation = false
                            sheetState.hide()

                        }



                    }) {
                        Box(modifier = Modifier.fillMaxWidth().padding(14.dp), contentAlignment = Alignment.Center) {
                            Text(text = "Yes", color = Color.White, fontWeight = FontWeight.SemiBold)

                        }

                    }
                    ApplicationButton(text = "No") {
                        scope.launch {
                            confirmation = false
                            sheetState.hide()
                        }
                    }
                } else {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)) {
                        Text(
                            text = "Choose action",
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                        IconButton(onClick = {
                            scope.launch {
                                confirmation = false
                                sheetState.hide()
                            }

                        }, modifier = Modifier.align(Alignment.CenterEnd)) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "",
                                tint = Color.White,

                            )
                        }
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp  )) {
                        ApplicationCard({
                            scope.launch {
                                confirmation = false
                                sheetState.hide()
                                navController.navigate("passwordDetail/$selectedOne")
                            }
                        }) {
                            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {

                                    navController.navigate("passwordDetail/$selectedOne")}) {
                                    Icon(painter = painterResource(R.drawable.baseline_edit_24), contentDescription = "edit", tint  = Color.White)
                                }
                                Text(text = "Edit", color = Color.White, fontWeight = FontWeight.SemiBold)
                            }



                        }
                        ApplicationCard(onClick = { confirmation = true }) {
                            Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.padding(8.dp)) {
                                IconButton(onClick = { confirmation = true }) {
                                    Icon(painter = painterResource(id = R.drawable.baseline_delete_24), contentDescription ="Delete", tint = Color.Red   )
                                }
                                Text(text = "Delete", color = Color.Red, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }

                }
            }

        }) {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate("createPassword") }, shape = CircleShape, containerColor =Color.Transparent) {
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(
                            Brush.linearGradient(listOf(LightGreen, DarkGreen)),
                            shape = CircleShape
                        )) {
                        Icon(Icons.Default.Add, contentDescription ="Add", tint = Color.White, modifier = Modifier.align(
                            Alignment.Center))
                    }

                }
            }, modifier = Modifier
                .fillMaxSize()
                .background(Color.Black))     {

                var search by remember {mutableStateOf("")}
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(it)) {
                    Header(text = "My passwords")


                    ApplicationTextField(placeholder = "search...", value = search, onValueChange = {search =it}, modifier = Modifier.padding(vertical = 14.dp), clear = {search = ""})
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp  )){
                        items(dataInformation.size) {
                            if(search == "" || dataInformation[it].title.lowercase() == search.lowercase())  {
                                ApplicationCard(onClick = {
                                    scope.launch {
                                        selectedOne = dataInformation[it].id
                                        sheetState.show()
                                    }

                                }) {

                                    Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.SpaceBetween) {
                                        Text(text = "${dataInformation[it].title}", color = Color.White, fontWeight = FontWeight.SemiBold)
                                        Text(text = "${dataInformation[it].username}", color = Color.White)
                                    }
                                }
                            }

                        }
                    }
                }}
        }




}