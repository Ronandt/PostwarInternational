package com.one.onens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController) {
    Column() {
        Column(modifier = Modifier

            .background(Color(0xFF191F2D))
            .padding(10.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { navController.navigateUp()}) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "" , tint = Color.White)

                }
            }

            Spacer(modifier = Modifier.height(20.dp ))
            Text(text = "SETTINGS", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))

        }
        Column(modifier = Modifier.padding(10.dp )) {
            Text(text = "Help centre", fontWeight = FontWeight.Bold, fontSize = 22.sp   )
            Row(modifier = Modifier.padding(10.dp   ).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Frequenty Added question")
                androidx.compose.material3.IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Right" )
                }
            }

            Text(text = "Contact")
            Spacer(modifier = Modifier.weight(1f    ))
            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                Text(text = "LOG OUT", fontWeight = FontWeight.SemiBold, color = Color.Black)
            }
        }


    }

}