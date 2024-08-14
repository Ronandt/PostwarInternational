package com.example.pocketvault

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun PasswordDetailScreen(navController: NavController, string: String?) {
    val context = LocalContext.current
    val database = AppDatabase.getDb(context)
    var note by remember {mutableStateOf<Note?>(null)}
    LaunchedEffect(Unit) {
        note = string?.toInt()?.let { database.noteDao().getByRealId(it) }

    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        if(note!=null) {
            var title by remember { mutableStateOf("${note?.title}") }
            var username by remember { mutableStateOf("${note?.username}") }
            var password by remember { mutableStateOf("${note?.password}") }
            var website by remember { mutableStateOf("${note?.website}") }
            Column(modifier = Modifier.fillMaxSize()) {
                Header(text = "View details", backNav = true, back = {navController.navigateUp()})

                Spacer(modifier = Modifier.height(100.dp))
                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    ApplicationTextField(placeholder = "title", value = title, onValueChange = {title = it}, clear = {title= ""} )
                    ApplicationTextField(placeholder = "username", value =  username, onValueChange = {username = it}, clear = {username = ""})
                    ApplicationTextField(placeholder = "password", value =  password, onValueChange =  {password = it}, passwordEnabled = true, clear = {password = ""})
                    ApplicationTextField(placeholder = "website", value = website, onValueChange = {website = it}, clear = {website = ""})

                }

                Spacer(modifier = Modifier.weight(1f))

                val scope = rememberCoroutineScope()
                ApplicationButton(text = "Save") {
                    scope.launch {
                        note.apply {
                            this?.title = title
                            this?.username = username
                            this?.password = password
                            this?.website =website
                        }?.let { database.noteDao().update(it) }
                    }

                    navController.navigateUp()
                }

            }
        }

    }
}