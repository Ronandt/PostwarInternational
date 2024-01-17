package com.example.international

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Text(text = "Skills change worlds", fontWeight = FontWeight.Bold, fontSize = 34.sp)
}