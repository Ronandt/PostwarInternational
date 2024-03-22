package com.example.youtubeclone

import android.widget.VideoView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DetailedScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = {
            VideoView(it).apply {

            }
        })
    }
}