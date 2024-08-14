package com.example.pocketvault

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun CreatePasswordScreen(navController: NavController) {
    val context = LocalContext.current
Column(modifier = Modifier
    .fillMaxSize()
    .background(Color.Black)) {
    var title by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    var website by remember {mutableStateOf("")}
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        Header(text = "Create Entry", backNav = true, back = {navController.navigateUp()})

        Spacer(modifier = Modifier.height(100.dp))
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            ApplicationTextField(placeholder = "title", value = title, onValueChange = {title = it} , clear = {title= ""})
            ApplicationTextField(placeholder = "username", value =  username, onValueChange = {username = it}, clear = {username = ""})
            ApplicationTextField(placeholder = "password", value =  password, onValueChange =  {password = it}, passwordEnabled = true, clear = {password = ""})
            ApplicationTextField(placeholder = "website", value = website, onValueChange = {website = it}, clear = {website = ""})

        }

        Spacer(modifier = Modifier.weight(1f))


            ApplicationButton(text = "Save") {
                scope.launch{
                    com.example.pocketvault.AppDatabase.getDb(context =context).noteDao().insert(Note(title = title, username = username, password= password, website = website, userKey = LoginManager(context).getSession()))
                    navController.navigateUp()
                }

            }

    }
}
}