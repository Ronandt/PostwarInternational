package com.example.worldskillssingapore

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.worldskillssingapore.ui.theme.RedColor
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(navController: NavController) {
    val context = LocalContext.current
    var oldPassword by remember {mutableStateOf("")}
    var newPassword by remember {mutableStateOf("")}
    var confirmNewPassword by remember {mutableStateOf("")}
    val scope = rememberCoroutineScope()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Change password", color = Color.White, fontWeight = FontWeight.SemiBold) }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White )
            }

        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = RedColor))

    }) {
        Column(modifier = Modifier.padding(it).padding(10.dp)) {
            Spacer(modifier = Modifier.height(20.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp  )) {
                OutlinedTextField(value = oldPassword, onValueChange = {oldPassword = it}, modifier = Modifier.fillMaxWidth(), label = {

                    Text(text = "Old password")
                } )
                OutlinedTextField(value = newPassword, onValueChange = {newPassword = it}, modifier = Modifier.fillMaxWidth(), label = {

                    Text(text = "New  password")
                } )
                OutlinedTextField(value = confirmNewPassword, onValueChange = {confirmNewPassword = it}, modifier = Modifier.fillMaxWidth(), label = {

                    Text(text = "Confirm New Password")
                } )


            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                scope.launch {
                    val m = APIManager()
                    val loginData = LoginSharedPreference(context).retrieveLogin()

                    val response = loginData?.accountid?.let { it1 -> m.changePassword(it1, accesskey = "1", cnfmpwd = confirmNewPassword, oldpwd = oldPassword, newpwd = newPassword) }
                    if(response?.status == "success") {
                        navController.navigateUp()
                        Toast.makeText(context, "Password has been updated", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, response?.result?.message, Toast.LENGTH_LONG).show()
                    }
                }


            }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                RedColor)) {
                Text(text = "Submit", color = Color.White)
            }
        }



    }

}