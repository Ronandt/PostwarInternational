package com.one.onens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable

 fun ProfileScreen(navController: NavHostController) {
  Column(modifier = Modifier
   .background(Color(0xFF161C28))
   .fillMaxSize()
   .padding(10.dp)) {
   Row(Modifier.fillMaxWidth()) {
    IconButton(onClick = {navController.navigate("settings") }) {
     Icon(imageVector = Icons.Default.Settings , contentDescription ="Settings", tint = Color.White
     )
    }
   }
   Text(text ="DELECT IAAC KUOK LI HENG", color = Color.White, fontWeight = FontWeight.Bold)
   Text(text = "******715 - Pre-Enlisttee", color = Color.White)
   Text(text = "Unit", modifier = Modifier.padding(vertical = 14.dp ), color = Color.White)
   Text(text = "-", color= Color.White)
   Spacer(modifier = Modifier.height(20.dp))

   Divider(modifier = Modifier.fillMaxWidth())
   Spacer(modifier = Modifier.height(30.dp))

  }

}