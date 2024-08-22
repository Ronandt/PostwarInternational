package edu.ws2022.aXX.am

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(1000)
        navController.navigate("events")
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
Image(painter = painterResource(id = R.drawable.images), contentDescription ="Image", contentScale = ContentScale.Crop,  modifier = Modifier.size(240.dp    ))
    }
}