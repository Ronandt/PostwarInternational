package com.one.onens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
Box(modifier = Modifier.fillMaxSize()) {
    Image(painter = painterResource(id = R.drawable.camo), contentDescription = "Fill", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop )
    Spacer(modifier = Modifier
        .align(Alignment.TopCenter)
        .fillMaxWidth()
        .height(80.dp)
        .background(brush = Brush.verticalGradient(listOf(Color.Black, Color(0x00000000)))))
    Spacer(modifier = Modifier
        .align(Alignment.BottomCenter)
        .fillMaxWidth()
        .height(320.dp)
        .background(brush = Brush.verticalGradient(listOf(Color(0x00000000), Color.Black))))
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.offset(y = 20.dp    , x = 10.dp )) {
        Image(painter = painterResource(id = R.drawable.onens), contentDescription = "Background", modifier = Modifier.size(38.dp  ), contentScale = ContentScale.Crop)
        Text(text = "One\nNS", color = Color.White, fontWeight = FontWeight.Bold)
    }
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1f    ))
        Text(text = "OneNS\nSUPPORTING\nYOUR NS\nEXPERIENCE", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 32.sp   )
        Spacer(modifier = Modifier.height(80.dp))
        Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFDD2939) ),onClick = {
                                                                                                    navController.navigate("home")
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "LOG IN VIA SINGPASS", color = Color.White, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(vertical = 6.dp    ))
        }
        Spacer(modifier = Modifier.height(60.dp))
    }
}
}