package com.example.pocketvault

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember {mutableStateOf("")}
    val context = LocalContext.current
    val passwordData = PasswordData(context)
    var password by remember {mutableStateOf("")}
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Header(text = "Authentication")
        Spacer(modifier = Modifier.height(200.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp  )) {
            ApplicationTextField(label = "username" , value =username , onValueChange = {username = it} , onClear = { username = "" }, modifier = Modifier.testTag("loginUsername"))
            ApplicationTextField(label = "password", value =  password, onValueChange =  {password = it}, onClear = { password = "" }, password = true, modifier = Modifier.testTag("loginPassword"))
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp  )) {
            ApplicationButton(text = "Login", onClick = {
                if(passwordData.login(username, password)) {
                    navController.navigate("passwords")
                } else {
                    Toast.makeText(context, "Invalid credentials", Toast.LENGTH_LONG).show()
                }

            })
            ApplicationButton(text = "Register", onClick = {navController.navigate("register")})
        }
    }
}