package com.one.onens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController) {
    Column(modifier = Modifier
        .height(200.dp)
        .background(Color(0xFF191F2D))) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "" , tint = Color.White)

        }
        Spacer(modifier = Modifier.height(20.dp ))
        Text(text = "SETTINGS", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
        Text(text = "Help centre", fontWeight = FontWeight.Bold)
        Text(text = "Frequenty Added question")
        Text(text = "Contact")
    }
}