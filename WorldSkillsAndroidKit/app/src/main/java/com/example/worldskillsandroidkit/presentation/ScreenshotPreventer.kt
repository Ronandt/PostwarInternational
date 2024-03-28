package com.example.worldskillsandroidkit.presentation

import android.app.ActionBar.LayoutParams
import android.app.Activity
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView

@Composable
fun ScreenPreventer() {
var view = LocalView.current
  var activityData =   (view.context as Activity).window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)

}