package com.example.scenario2

import android.webkit.WebView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.scenario2.ui.theme.appBarColor

@Composable
fun FeedbackScreen(navController: NavController) {
    Scaffold(topBar = {
        androidx.compose.material.TopAppBar(backgroundColor = appBarColor) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "OrderMyT", fontWeight = FontWeight.Bold)
                IconButton(onClick = {navController.navigate("checkout")  }) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Checkout", tint = Color.Black)
                }
            }
        }
    }) {
        Column(modifier = Modifier.padding(it)) {
            AndroidView(factory = {
                WebView(it).apply {
                    settings.javaScriptEnabled = true
                    this.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSeeeOtorPPURR_FcrsLu7aaih-h2jmGtk4yH9skDdOv6_9KyQ/viewform?usp=sf_link")
                }
            }, modifier =  Modifier.fillMaxSize())
        }

    }

}