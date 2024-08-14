package com.example.pocketvault

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dao = AppDatabase.getDb(context = context)
Column(modifier = Modifier.fillMaxSize()) {
Header(text = "Authentication")
    Spacer(modifier = Modifier.height(100.dp))
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ApplicationTextField(placeholder = "username", value = username, onValueChange = {username = it} , clear = {username = ""})
        ApplicationTextField(placeholder = "password", value = password, onValueChange = {password = it}, passwordEnabled = true, clear = {password= ""})

    }
    Spacer(modifier = Modifier.height(60.dp))
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ApplicationButton(text = "Login") {
            if(username=="" || password=="") {
                Toast.makeText(context, "Fields are empty", Toast.LENGTH_LONG).show()
                return@ApplicationButton
            }
            scope.launch {
                val user = dao.userDao().getUser(username)
                if(user.isEmpty()) {
                    Toast.makeText(context, "User credentials does not exist", Toast.LENGTH_LONG).show()
                    return@launch
                }
                if(user.first().password == password) {
                    LoginManager(context = context).createSession(user.first().id)
                    navController.navigate("passwords")
                } else {
                    Toast.makeText(context, "User credentials does not exist", Toast.LENGTH_LONG).show()
                }


            }





        }
        ApplicationButton(text = "Register") {
            navController.navigate("register")
        }
    }
}
}

@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    LoginScreen(navController = rememberNavController())
}