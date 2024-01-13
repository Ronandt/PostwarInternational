package com.example.shoepanic


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.shoepanic.ui.theme.DarkBlue
import com.example.shoepanic.ui.theme.Header
import com.example.shoepanic.ui.theme.MainButton

@Composable
fun PhysicalVerifiedScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(R.drawable.baseline_verified_24), contentDescription = "verified", tint = DarkBlue)
            Header(text = "Scanned The Physical QR Code")
            Text(text = "Name: Jane Doe (id: 59494)")
            Text(text = "Position #0")
            MainButton(text = "Go back to home", onClick = { navController.navigate("home") })
        }
    }
}