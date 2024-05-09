package com.example.pocketvault

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
fun EditScreen(navController: NavController,id  : Int) {
    val context = LocalContext.current
    val passwordData = PasswordData(context)
    val data = passwordData.getPasswords()?.get(id)
    var title by remember { mutableStateOf(data?.title) }
    var username by remember { mutableStateOf(data?.username    ) }
    var password by remember { mutableStateOf(data?.password) }
    var website by remember { mutableStateOf(data?.website  ) }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        Header(text = "Edit entry", backNavigation = true, onBackNavigation = {
            navController.navigateUp()
        })
        Spacer(modifier = Modifier.height(6.dp))

        title?.let { ApplicationTextField(label = "title", value = it, onValueChange = {title = it} , onClear = { title = "" }, modifier = Modifier.testTag("editTitle")) }
        username?.let { ApplicationTextField(label = "username", value = it, onValueChange = {username = it}, onClear = { username = ""}, modifier = Modifier.testTag("editUsername")) }
        password?.let { ApplicationTextField(label = "password", value = it, onValueChange = {password = it} , onClear = { password = "" }, password = true, modifier = Modifier.testTag("editPassword")) }
        website?.let { ApplicationTextField(label = "website", value = it, onValueChange =  {website = it}, onClear = { website = "" }, modifier = Modifier.testTag("editWebsite")) }
        Spacer(modifier = Modifier.weight(1f))
        ApplicationButton(text = "Save") {
            username?.let { title?.let { it1 -> password?.let { it2 -> website?.let { it3 -> Password(title = it1, username = it, password = it2, website = it3) } } } }
                ?.let {
                    passwordData.editData(id,
                        it
                    )
                }
            navController.navigateUp()
        }


    }
}