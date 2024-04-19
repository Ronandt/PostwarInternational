package com.example.worldskills_c_am


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CreateScreen(navController: NavController) {
    var titleData by remember {mutableStateOf("")}
    var bodyTextData by remember {mutableStateOf("")}
    Column(modifier = Modifier
        .fillMaxSize()
        .background(androidx.compose.ui.graphics.Color.Black)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Close, contentDescription = "close",tint = androidx.compose.ui.graphics.Color.Black)

            }
            Button(onClick = { /*TODO*/ }, enabled =titleData != "") {
                Text(text = "Next")
            }
        }
        BasicTextField(value = titleData, onValueChange ={titleData = it}, singleLine = true, textStyle = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Color.LightGray

        ))
        Spacer(modifier = Modifier.height(15.dp))
        BasicTextField(value = bodyTextData, onValueChange = {bodyTextData = it}, textStyle = TextStyle(androidx.compose.ui.graphics.Color.LightGray ) )
        Spacer(modifier = Modifier.weight(1f))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            repeat(4) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null , tint = androidx.compose.ui.graphics.Color.White )
                }
            }

        }
    }
}