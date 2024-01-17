package com.example.international

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international.ui.theme.HotPink

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SplashScreen(navController: NavController) {
Column(modifier = Modifier
    .fillMaxSize()
    .background(HotPink)) {
    var swipe = rememberSwipeableState(initialValue =0)
    val infiniteTransition = rememberInfiniteTransition()
    val move by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(animation = tween(1000, easing = LinearEasing),
        repeatMode = RepeatMode.Reverse)

    )
    LaunchedEffect(swipe.currentValue, swipe.offset, swipe) {
        println(swipe.offset)
        if(swipe.currentValue == 1) {
            navController.navigate("about")
        }
    }

    Box(modifier = Modifier.fillMaxSize().swipeable(state = swipe, anchors =mapOf(0f to 0, 10000f to 1), reverseDirection = true, orientation = Orientation.Vertical).offset{
        IntOffset(0, -swipe.offset.value.toInt())
}) {

        val trianglePath = androidx.compose.ui.graphics.Path().apply {
            moveTo(2000f/2f, 0f)
            lineTo(3000f, 2000f)
            lineTo(-1190f, 2000f)
        }
        Canvas(
            Modifier
                .align(Alignment.TopCenter)
                .offset(x = -483.5.dp, y = 35.dp), onDraw = {
            drawPath(path = trianglePath, color = Color.White)

        })
        Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Master Skills Change The World", fontWeight = FontWeight.Black, fontSize = 32.sp, modifier = Modifier.width(300.dp), textAlign = TextAlign.Center, lineHeight = 35.sp)
            Spacer(modifier = Modifier.height(30.dp))
            Image(painter = painterResource(R.drawable.world), contentDescription = "World", contentScale = ContentScale.Crop, modifier = Modifier.size(500.dp, 300.dp))
        }
        Image(painterResource(R.drawable.lamp), contentDescription = null, modifier = Modifier
            .align(Alignment.TopCenter)
            .offset(x = 16.dp)
            .size(150.dp))

        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Down", modifier = Modifier
            .size(50.dp)
            .align(Alignment.BottomCenter)
            .offset(y = 0.dp + -50.dp)
            .offset(y = move.dp), tint = Color.Black, )

    }
}
}