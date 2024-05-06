package com.example.stroopy

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stroopy.ui.theme.PrimaryGreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = Unit) {
        delay(20)
        navController.navigate("nickname")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(R.drawable.img), contentDescription = "logo", modifier = Modifier
            .size(340.dp)
            .offset(-160.dp, y = 300.dp), contentScale = ContentScale.Crop)
        Column(modifier = Modifier
            .fillMaxSize()
            .background(PrimaryGreen), horizontalAlignment =Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp  )) {
            Spacer(modifier = Modifier.height(60.dp))
            Image(painter = painterResource(id = R.drawable.img), contentDescription = "Imge", contentScale = ContentScale.Crop, modifier = Modifier.size(80.dp ))
            Text(
                text = "Stroopy",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(text = "React to colors in 2 seconds", color = Color.White)
            
        }
    }

}