package com.example.worldskillsandroidkit.presentation

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
import com.example.worldskillsandroidkit.R
import kotlinx.coroutines.delay

class SplashScreenManager(private val image: Int, private val modifier: Modifier = Modifier) {
    @Composable
    fun SplashScreen(onNavigate: () -> Unit) {
        LaunchedEffect(Unit) {
            delay(2000)
            onNavigate()

        }

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().padding(20.dp)) {
            Image(painter = painterResource(image), contentDescription ="Logo", modifier = Modifier.fillMaxWidth().then(modifier), contentScale = ContentScale.Crop)
        }
    }
}