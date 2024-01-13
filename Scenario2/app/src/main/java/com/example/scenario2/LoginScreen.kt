package com.example.scenario2

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.scenario2.ui.theme.primaryButtonColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

//oops i forgot to record the first 5 mins but what I've done here is navcontroller and small part of the login screen
@Composable
fun LoginScreen(navController: NavController ) {
    var mobile by remember {mutableStateOf("")}
    val scope= rememberCoroutineScope()
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE)

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(R.drawable.bubbletea), contentDescription =  "Bubble tea", contentScale = ContentScale.Crop, modifier = Modifier
            .fillMaxWidth()
            .height(400.dp))
        Column(modifier = Modifier.padding(10.dp)) {
            OutlinedTextField(value = mobile, onValueChange = {mobile = it}, label = { Text(text = "Mobile")}, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.weight(1f) )
            androidx.compose.material.Button(
                onClick = {
                    println("ff")
                    if(!Pattern.matches(android.util.Patterns.PHONE.pattern(), mobile)) {
                        Toast.makeText(context, "Invalid mobile number", Toast.LENGTH_LONG).show()
                        return@Button
                    }
                    scope.launch(Dispatchers.IO){

                        var result = Api.login(mobile)
                        if(result.getString("status") == "success") {
                            with(sharedPref.edit()) {
                                this.putString("info", result.toString())
                                this.apply()
                            }
                            withContext(Dispatchers.Main) {
                                navController.navigate("order")

                            }

                        } else {
                            println("Wrong num")
                            Toast.makeText(context, result.getString("message"), Toast.LENGTH_LONG).show()
                        }
                    }

                    /*navController.navigate("order")*/},
                colors = ButtonDefaults.buttonColors(backgroundColor = primaryButtonColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "SUBMIT")
            }
        }
    }
}