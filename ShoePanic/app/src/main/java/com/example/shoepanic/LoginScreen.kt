package com.example.shoepanic


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shoepanic.ui.theme.AuthenticationTextField
import com.example.shoepanic.ui.theme.DarkBlue
import com.example.shoepanic.ui.theme.Header
import com.example.shoepanic.ui.theme.LightningBlue
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var username by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 18.dp), contentAlignment = Alignment.Center, ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Header("Login")
            Text(text = "Login now to start queueing", modifier = Modifier.padding(bottom = 10.dp))


            AuthenticationTextField(
                value = username,
                onValueChange ={username = it} ,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "user")
                },
                placeholder = "Username",

                )

            AuthenticationTextField(
                value = password,
                onValueChange ={password = it} ,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock   ")
                },
                placeholder = "Password",
                visualTransformation = true
            )
            Button(onClick = { navController.navigate("staffHome")}, modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp), colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)) {
                Text(text = "Login", fontWeight = FontWeight.SemiBold, color = Color.White, fontSize = 18.sp)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
                Button(onClick = { navController.navigate("changePassword") }, colors = ButtonDefaults.buttonColors(containerColor = Color.Black), modifier = Modifier.width(170.dp)) {
                    Text(text = "Change Password", color = Color.White, )
                }
                Button(onClick = { navController.navigate("resetPassword")}, colors = ButtonDefaults.buttonColors(containerColor = Color.Black), modifier = Modifier.width(170.dp)) {
                    Text(text = "Reset Password", color = Color.White)
                }
            }
        }





    }
}