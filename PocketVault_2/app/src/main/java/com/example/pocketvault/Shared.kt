package com.example.pocketvault

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.pocketvault.ui.theme.DarkGreen
import com.example.pocketvault.ui.theme.LightGray
import com.example.pocketvault.ui.theme.LightGreen

@Composable
fun Header(text: String, backNav: Boolean = false    ,back: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxWidth()
    ) {
        if(backNav) {
            IconButton(onClick = back, modifier = Modifier.align(Alignment.CenterStart)) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Bck", tint = Color.White)

            }
        }
        Text(text = text, color = Color.White, modifier = Modifier
            .padding(10.dp)
            .align(Alignment.Center))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationTextField(placeholder: String, value: String, onValueChange: (String) -> Unit, passwordEnabled: Boolean = false, clear: () -> Unit,modifier: Modifier=  Modifier ) {
    var password by remember {mutableStateOf(passwordEnabled)}
    TextField(textStyle = TextStyle(color = Color.White),value = value, onValueChange =onValueChange, visualTransformation = if(passwordEnabled) PasswordVisualTransformation() else VisualTransformation.None, label = {
        Text(text = placeholder, color = Color.White)
    }, colors = TextFieldDefaults.textFieldColors(containerColor = LightGray, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent), modifier = Modifier
        .fillMaxWidth()
        .then(modifier), trailingIcon = {
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            if(passwordEnabled) {
                IconButton(onClick = { password = !password}) {
                    if(password) {
                        Icon(painter = painterResource(R.drawable.baseline_visibility_24), contentDescription = "see", tint = Color.White)
                    } else {
                        Icon(painter = painterResource(R.drawable.baseline_visibility_off_24), contentDescription = "see", tint = Color.White)
                    }
                }


            }
            if(value != "") {
                IconButton(onClick = { clear() }) {
                    Icon(Icons.Default.Close, contentDescription = "close", tint = Color.White)
                }
            }

        }

    })

}

@Composable
fun ApplicationButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick   , shape = RoundedCornerShape(8.dp) , modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(0.dp)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        LightGreen, DarkGreen
                    ),
                ), RoundedCornerShape(8.dp)
            )) {
            Text(text = text, color = Color.White,fontWeight = FontWeight.SemiBold,modifier = Modifier.align(Alignment.Center))
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationCard(onClick: () -> Unit = {}, content: @Composable () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), onClick = onClick,colors = CardDefaults.cardColors(containerColor = LightGray)) {
        content()
    }
}
