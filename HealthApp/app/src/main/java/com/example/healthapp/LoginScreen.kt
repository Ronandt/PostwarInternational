package com.example.healthapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.healthapp.ui.theme.LightGray

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    Column(modifier = Modifier.fillMaxSize()) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Black), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Text(text = "MY Health DATA", fontWeight = FontWeight.Bold, fontSize = 26.sp, color = Color.White, modifier = Modifier.padding(20.dp    ))

    }
    Spacer(modifier = Modifier.height(10.dp))

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.symbol), contentDescription = "Symbol" , modifier = Modifier.size(140.dp))
        Text(text = "Please enter your information", color = Color.DarkGray, modifier = Modifier.padding(20.dp))
        ApplicationTextField(
            value = username ,
            onValueChange = {username = it},
            placeholder = "Username" ,
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Person", tint = Color.DarkGray)},
            clickable = false
        )
        ApplicationTextField(value = password, onValueChange = {password = it} , placeholder = "Password" , clickable = false , leadingIcon = { Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = "lock", tint = Color.DarkGray
        )})
        Spacer(modifier = Modifier.height(20.dp))
        ApplicationTextButton(text = "Sign in ") {
            navController.navigate("onboard")
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = LightGray)
            .padding(50.dp), ) {
            BlackTextButton(text = "Sign up") {
                navController.navigate("signUp")
            }
            Spacer(modifier = Modifier.height(10.dp))
            WhiteTextButton(text = "Password Reset") {
                navController.navigate("passwordReset")
            }

        }
    }

}
}