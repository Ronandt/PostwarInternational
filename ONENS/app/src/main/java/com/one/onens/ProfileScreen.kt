package com.one.onens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable

 fun ProfileScreen(navController: NavHostController) {
  Column(modifier = Modifier.padding(10.dp)) {
   Text(text ="DELECT IAAC KUOK LI HENG", color = Color.White, fontWeight = FontWeight.Bold)
   Text(text = "******715 - Pre-Enlisttee")
   Text(text = "Unit", modifier = Modifier.padding(vertical = 14.dp ))
   Text(text = "-")
  }
}