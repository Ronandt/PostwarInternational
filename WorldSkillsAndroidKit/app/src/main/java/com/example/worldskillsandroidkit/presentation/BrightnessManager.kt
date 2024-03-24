package com.example.worldskillsandroidkit.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun HandleBrightness(activity: Activity) {
    val context = LocalContext.current

    (context as Activity).window.attributes =     (context as Activity).window.attributes.apply {
        screenBrightness = 0.9f
    }
}