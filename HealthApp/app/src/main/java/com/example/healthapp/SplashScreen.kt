package com.example.healthapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var imageAnimation by remember {mutableStateOf(false)}
    var textAnimation by remember { mutableStateOf(false    )}
    LaunchedEffect(Unit) {
        delay(1000)
        imageAnimation = true
        delay(500)
        textAnimation = true
        delay(500)
        navController.navigate("login")

    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.spacedBy(14.dp ), horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(visible = imageAnimation) {
                Image(painter = painterResource(R.drawable.symbol), contentDescription = "Splash logo", contentScale = ContentScale.Crop, modifier = Modifier.size(160.dp   ))
            }
            AnimatedVisibility(visible = textAnimation) {
                Text(text = "MY Health DATA", fontWeight = FontWeight.Bold, fontSize = 26.sp)
            }



        }
    }
}