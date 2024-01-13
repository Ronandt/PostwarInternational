package com.example.shoepanic

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@Composable
fun FeedbackScreen(navController: NavController) {
    AndroidView(factory = {
                          WebView(it)
    }, modifier = Modifier.fillMaxSize())
}