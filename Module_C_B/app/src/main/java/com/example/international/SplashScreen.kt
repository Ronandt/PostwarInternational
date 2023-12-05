package com.example.international

import android.icu.text.Normalizer.NO
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import java.lang.Math.sqrt

@Composable
fun SplashScreen(navController: NavController) {
    val swipeableState = rememberDraggableState(onDelta = {})
    //NO DRAGOABEL STATE????
    val value by animateDpAsState(targetValue = 50.dp,
        
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 300),
            repeatMode = RepeatMode.Reverse
        ))
    
    LaunchedEffect(key1 = Unit) {
        delay(1000)
        navController.navigate("about")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFD51067))) {
        Box {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val path = Path().apply {
                    val traingleSide = size.width
                    val height = traingleSide * (sqrt(3.0) / 2.0).toFloat()
                    moveTo(traingleSide/2f, 0f)
                    lineTo(-800f, height)
                    lineTo(traingleSide * 1.31f, height)
                    close()
                }
                drawPath(path, Color.White)
            }
            Image(painter = painterResource(R.drawable.lamp) , contentDescription = "Lamp", modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = -160.dp))


            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxSize()
                .offset(y = 100.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Master Skills Change the World", fontWeight = FontWeight.Black, fontSize = 34.sp, modifier = Modifier.width(280.dp), textAlign = TextAlign.Center, lineHeight = 30.sp)
                    Image(painter = painterResource(R.drawable.countries), contentDescription =  "Countires", modifier = Modifier.size(500.dp, 400.dp))
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Down", modifier= Modifier
                        .offset(y = value)
                        .size(50.dp))
                }
            }
        }





        }

}