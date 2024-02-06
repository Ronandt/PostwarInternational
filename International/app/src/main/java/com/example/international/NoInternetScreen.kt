package com.example.international

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.international.ui.theme.HotPink

@Composable
fun NoInternetScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(R.drawable.no_internet), contentDescription = "No internet", modifier = Modifier.size(150.dp    ), contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.height(18.dp))
            Text(text = "You have no internet.", fontWeight = FontWeight.SemiBold, fontSize = 24.sp, color = HotPink)
            Text(text = "You must be connected to the internet to view this page", fontSize = 18.sp, color = Color.DarkGray)
        }
    }

}