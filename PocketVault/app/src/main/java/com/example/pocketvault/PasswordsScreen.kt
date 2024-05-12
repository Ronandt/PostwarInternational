package com.example.pocketvault

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pocketvault.ui.theme.DarkGray
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordsScreen(navController: NavController) {
    var search by remember {mutableStateOf(""   )}
    var currentData by remember {mutableStateOf(-1)}
    var bottom by remember {mutableStateOf(false)}
    var bottomSheet = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var change by remember {mutableStateOf(false)}
    var passwords = remember {PasswordData(context).getPasswords()?.toMutableStateList()}
    var deleteFlag = remember {mutableStateListOf<Int>()}

    val passwordData = remember {PasswordData(context = context ) }
    LaunchedEffect(Unit, change) {
        while(true) {
            delay(1000)
            passwords?.clear()
            passwordData.getPasswords()?.let {

                passwords?.addAll(it) }
        }

    }
    LaunchedEffect(key1 = Unit) {

        bottomSheet.hide()
    }
    if(bottom) {
        ModalBottomSheet(containerColor = Color.Black,onDismissRequest = {
            scope.launch {
                bottomSheet.hide()
            }
            bottom = false
        }, sheetState = bottomSheet) {
            var sheetStateType by remember {mutableStateOf(false)}

            if(sheetStateType) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = "Are you sure?", fontWeight = FontWeight.Bold, color = Color.White)
                        IconButton(onClick = {
                            scope.launch {
                                bottomSheet.hide()
                                sheetStateType = false
                                bottom = false

                            }
                        }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "close", tint = Color.White)

                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(modifier = Modifier.fillMaxWidth(),colors = CardDefaults.cardColors(containerColor = DarkGray), onClick = {
                        passwordData.deleteData(currentData)
                        change = !change
                        scope.launch {
                            bottomSheet.hide()
                        }

                        sheetStateType = false
                        bottom = false
                        deleteFlag.add(currentData)
                    }) {
                        Column(modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                              
                                Text(text = "Yes", fontWeight = FontWeight.Bold, color = Color.White)

                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    ApplicationButton(text = "No") {
                        sheetStateType = false
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    
                }
            } else {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = "Choose action", fontWeight = FontWeight.Bold, color = Color.White)
                        IconButton(onClick = {
                            scope.launch {
                                bottomSheet.hide()
                                sheetStateType = false

                            }
                        }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "close", tint = Color.White)

                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(modifier = Modifier.fillMaxWidth(),colors = CardDefaults.cardColors(containerColor = DarkGray), onClick = {
                        navController.navigate("edit/$currentData")

                    }) {
                        Column(modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp   )) {
                                Icon(imageVector = Icons.Default.Edit, contentDescription ="Edit", tint = Color.White )
                                Text(text = "Edit", fontWeight = FontWeight.Bold, color = Color.White)

                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Card(modifier = Modifier.fillMaxWidth(),colors = CardDefaults.cardColors(containerColor = DarkGray), onClick = {

                        sheetStateType = true

                    }) {
                        Column(modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp   )) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription ="Edit", tint = Color.Red)
                                Text(text = "Delete", fontWeight = FontWeight.Bold, color = Color.Red )

                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))


                }

            }
        }
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        Spacer(modifier = Modifier.height(10.dp))
        Header(text = "My passwords")
        Spacer(modifier = Modifier.height(20.dp))

        ApplicationTextField(label = "search...", value = search , onValueChange = {search = it} , onClear = { search = "" }, modifier = Modifier.testTag("search"))
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn() {
            passwords?.size?.let {
                items(it)  {
                    if(search == "" || passwords[it]?.title?.lowercase()?.contains(search.lowercase()) == true) {
                        if(!deleteFlag.contains(it)) {
                            Spacer(modifier = Modifier.height(8.dp))
                            passwords[it]?.title?.let { it1 ->
                                passwords[it]?.username?.let { it2 ->
                                    ApplicationCard(header = it1, subHeader = it2) {
                                        scope.launch {
                                            bottomSheet.show()
                                            currentData = it
                                            bottom = true
                                        }


                                    }
                                }
                            }
                        }

                    }


                }
            }
        }
    }

}