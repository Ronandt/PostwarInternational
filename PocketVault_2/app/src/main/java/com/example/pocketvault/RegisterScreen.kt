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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val scope  = rememberCoroutineScope()
    var userDao = AppDatabase.getDb(context)
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember {mutableStateOf("")}
    Column(modifier = Modifier.fillMaxSize()) {
        Header(text = "Authentication", back = {navController.navigateUp()}, backNav = true)
        Spacer(modifier = Modifier.height(100.dp))
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            ApplicationTextField(placeholder = "username", value = username, onValueChange = {username = it}, clear = {username = ""})
            ApplicationTextField(placeholder = "password", value = password, onValueChange = {password = it}, passwordEnabled = true, clear = {username = ""})
            ApplicationTextField(placeholder = "confirm password", value = confirmPassword,  onValueChange = {confirmPassword= it}, passwordEnabled = true, clear = {username = ""})
        }
        Spacer(modifier = Modifier.height(60.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            ApplicationButton(text = "Register") {
                if(username=="" || password =="" || confirmPassword=="") {
                    Toast.makeText(context, "Fields are empty", Toast.LENGTH_LONG).show()

                }
                if(password != confirmPassword) {
                    Toast.makeText(context, "Confirm password is not the same as the password", Toast.LENGTH_LONG).show()

                    return@ApplicationButton
                }
                scope.launch {
                    if(userDao.userDao().getUser(username).isNotEmpty()) {
                        Toast.makeText(context, "Username exists", Toast.LENGTH_LONG).show()
                        return@launch
                    }
                    userDao.userDao().insert(User(username = username, password = password))
                    navController.navigate("login")
                }


            }
        }
    }
}