package com.example.worldskillsandroidkit.presentation

import android.app.Activity
import android.graphics.Bitmap
import android.os.Handler
import android.provider.MediaStore
import android.view.PixelCopy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toAndroidRect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
//import androidx.glance.Button

@Composable
fun Bounds() {
    var composableBounds by remember {mutableStateOf<Rect?>(null)}
    val context = LocalContext.current
    Column(modifier = Modifier
        .size(100.dp)
        .onGloballyPositioned {
            composableBounds = it.boundsInWindow()
        }) {
        /*
        Button(text = "Yes", onClick = {
            var bitmap = composableBounds?.width?.toInt()
                ?.let { composableBounds?.height?.toInt()
                    ?.let { it1 -> Bitmap.createBitmap(it, it1, Bitmap.Config.ARGB_8888) } }

            if (bitmap != null) {
                PixelCopy.request((context as Activity).window, composableBounds?.toAndroidRect(), bitmap,{}, Handler(context.mainLooper))
            }
            MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "", "")

        })*/
    }
}