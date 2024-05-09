@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pocketvault

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import com.example.pocketvault.ui.theme.DarkGray
import com.example.pocketvault.ui.theme.DarkGreen
import com.example.pocketvault.ui.theme.LightGreen


@Composable
fun Header(text: String, backNavigation: Boolean = false, onBackNavigation: () -> Unit = {}) {
    if(backNavigation) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)) {
            IconButton(onClick = onBackNavigation) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="back", tint = Color.White )
            }
            Text(text = text, color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(10.dp  ))
            Spacer(modifier = Modifier.width(0.dp   ))
        }
    } else {
        Row(horizontalArrangement = Arrangement.Center,modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)) {
            Text(text = text, color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(10.dp  ))
        }
    }

}


@Composable
fun ApplicationTextField(label: String,value: String, onValueChange: (String) -> Unit, onClear: () -> Unit, password: Boolean =  false, modifier: Modifier = Modifier) {
    var showPassword by remember {mutableStateOf(false)}
    OutlinedTextField(value =  value, onValueChange = onValueChange, modifier = Modifier.fillMaxWidth().then(modifier), colors = TextFieldDefaults.colors(
        focusedContainerColor = DarkGray,
        unfocusedContainerColor = DarkGray,
        focusedPlaceholderColor = Color.LightGray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
        
        
    ), textStyle = TextStyle(color = Color.White),label = {
               Text(text = label, color = Color.White)
    },visualTransformation = if(showPassword || !password) VisualTransformation.None else PasswordVisualTransformation(), trailingIcon = {
        Row(horizontalArrangement = Arrangement.spacedBy(3.dp   )) {
            if(value != "") {
                IconButton(onClick = onClear) {
                    Icon(imageVector = Icons.Default.Close, contentDescription ="Close", tint = Color.White)

                }
            }

            if(password) {
                if(showPassword) {
                    IconButton(onClick = { showPassword = !showPassword}) {
                        Icon(painterResource(R.drawable.baseline_visibility_24), contentDescription = "Show pasword" , tint = Color.White)
                    }
                } else {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(painterResource(R.drawable.baseline_visibility_off_24), contentDescription ="dont show", tint = Color.White)
                    }
                }


            }
        }

        
        
    })
}

@Composable
fun ApplicationButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick    , contentPadding = PaddingValues(0.dp),shape = RoundedCornerShape(10.dp ), modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = LightGreen)) {
        Box(modifier = Modifier
            .requiredWidth(400.dp)
            .height(50.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        LightGreen, DarkGreen
                    )
                ), shape = RoundedCornerShape(10.dp)
            ), contentAlignment = Alignment.Center) {
            Text(text = text, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }

}


@Composable
fun ApplicationCard(header: String, subHeader: String, onClick: ()-> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = DarkGray), onClick = onClick) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp )) {
            Text(text = header, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = subHeader, color = Color.LightGray, fontSize = 12.sp)
        }
    }
}

@Composable
fun SimpleApplicationCard(header: String, onClick: ()-> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = DarkGray), onClick = onClick) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = header, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DangerApplicationCard(header: String, onClick: ()-> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = DarkGray), onClick = onClick) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red     )
            Text(text = header, color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 16.sp)


        }
    }
}