package com.example.international_module_c_am

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.international_module_c_am.ui.theme.DarkBlue
import kotlinx.coroutines.delay

@Composable
fun NoInternetScreen() {
    val context = LocalContext.current
    var confirmNoInternet by remember {mutableStateOf(false)}


    var sharedPref by remember{ mutableStateOf( context.getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("darkMode", false    ))}
    LaunchedEffect(key1 = Unit ) {
        while(true) {
            delay(500)
            sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("darkMode", false)
        }
    }
    LaunchedEffect(Unit) {
        delay(500)
        confirmNoInternet = true
    }

    AnimatedVisibility(confirmNoInternet) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(R.drawable.no_internet_2), contentDescription = "No internet")
                Text(text = "No internet! Please check your connection", fontWeight = FontWeight.SemiBold, color = if(sharedPref) Color.White else DarkBlue)
            }
        }
    }


}