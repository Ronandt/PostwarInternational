package com.example.pocketvault

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CreatePasswordScreen(navController: NavController) {
    val context = LocalContext.current
    val passwordData = PasswordData(context)
    var title by remember {mutableStateOf("")}
    var username by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var website by remember {mutableStateOf("")}
Column(modifier = Modifier
    .fillMaxSize()
    .background(Color.Black)) {
    Header(text = "Create entry", backNavigation = true, onBackNavigation = {
        navController.navigateUp()
    })
    Spacer(modifier = Modifier.height(6.dp))

    ApplicationTextField(label = "title", value = title , onValueChange = {title = it} , onClear = { title = "" }, modifier = Modifier.testTag("createTitle"))
    ApplicationTextField(label = "username", value = username, onValueChange = {username = it}, onClear = { username = ""}, modifier = Modifier.testTag("createUsername"))
    ApplicationTextField(label = "password", value = password , onValueChange = {password = it} , onClear = { password = "" }, password = true, modifier = Modifier.testTag("createPassword"))
    ApplicationTextField(label = "website", value = website, onValueChange =  {website = it}, onClear = { website = "" }, modifier = Modifier.testTag("createWebsite"))
    Spacer(modifier = Modifier.weight(1f))
    ApplicationButton(text = "Save") {
        passwordData.putPasswords(Password(title = title, username = username, password = password, website = website))
        Toast.makeText(context, "Added password!", Toast.LENGTH_SHORT).show()
        navController.navigateUp()
    }


}
}