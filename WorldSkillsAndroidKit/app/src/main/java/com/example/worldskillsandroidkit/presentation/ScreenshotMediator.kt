package com.example.worldskillsandroidkit.presentation

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.toAndroidRect

sealed class ImageResult {
    data class Error(val exception: Exception) : ImageResult()
    data class Success(val data: Bitmap) : ImageResult()
}
fun View.screenshot(
    bounds: Rect
): ImageResult {

    try {

        val bitmap = Bitmap.createBitmap(
            bounds.width.toInt(),
            bounds.height.toInt(),
            Bitmap.Config.ARGB_8888,
        )

            PixelCopy.request(
                (this.context as Activity).window,
                bounds.toAndroidRect(),
                bitmap,
                {},
                Handler(Looper.getMainLooper())
            )

        return ImageResult.Success(bitmap)
    } catch (e: Exception) {
        return ImageResult.Error(e)
    }
}