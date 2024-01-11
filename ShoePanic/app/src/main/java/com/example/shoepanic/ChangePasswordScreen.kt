package com.example.shoepanic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shoepanic.ui.theme.AuthenticationTextField
import com.example.shoepanic.ui.theme.Header
import com.example.shoepanic.ui.theme.MainButton

@Composable
fun ChangePasswordScreen(navController: NavController) {
    var username by remember {mutableStateOf("")}
    var currentPassword by remember {mutableStateOf("")}
    var newPassword by remember {mutableStateOf("")}
    var confirmNewPassword by remember {mutableStateOf("")}

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp), contentAlignment = Alignment.Center) {
        IconButton(onClick = { navController.navigateUp() }, modifier = Modifier.align(Alignment.TopStart).offset(x = -10.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "go back",

            )

        }
        Column(modifier  = Modifier.fillMaxWidth()) {
            Header("Change Password")
            Text("Want to change password? Right here!")
            AuthenticationTextField(
                    value = username,
            onValueChange = {username  = it},
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
            placeholder ="Username"
            )
            AuthenticationTextField(
                value = currentPassword,
                onValueChange = {currentPassword  = it},
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password" ) },
                placeholder ="Current Password",
                visualTransformation = true
            )
            AuthenticationTextField(
                value = newPassword,
                onValueChange = {newPassword  = it},
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password" ) },
                placeholder ="New Password",
                visualTransformation = true
            )
            AuthenticationTextField(
                value = confirmNewPassword,
                onValueChange = {confirmNewPassword  = it},
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password" ) },
                placeholder ="Confirm new password",
                visualTransformation = true
            )
            MainButton(text = "Change", onClick = { /*TODO*/ })
        }
    }
}