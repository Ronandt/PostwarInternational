package com.example.international_a_am

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("events")

    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Image(painter = painterResource(R.drawable.worldskills), contentDescription ="Worldskills Logo", modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.Crop)
    }
}