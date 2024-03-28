package com.example.worldskillsandroidkit.presentation

import android.app.Activity
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.PixelCopy
import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.toAndroidRect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GridList(rowLength: Int, listSize: Int ,contentCallback: (Int) -> Unit ,modifier: Modifier = Modifier) {
    FlowRow(maxItemsInEachRow = rowLength, horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = modifier) {
        repeat(listSize) {
            contentCallback(it)
        }

    }

    val view: View = LocalView.current
    val context = LocalContext.current
    var composableBounds by remember {
        mutableStateOf<Rect?>(null)
    }


    Card(modifier = Modifier
        .onGloballyPositioned {
            composableBounds = it.boundsInWindow()
        }){}


    LaunchedEffect(key1 = Unit ) {
        val bitmap =   Bitmap.createBitmap(
            composableBounds?.width?.toInt()!!,
            composableBounds?.height?.toInt()!!,
            Bitmap.Config.ARGB_8888,
        )
        val result = composableBounds?.let {

            PixelCopy.request(
                (view.context as Activity).window,
                it.toAndroidRect(),
                bitmap,
                {},
                Handler(Looper.getMainLooper())) }




        MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, " Ticket", "Ticket for whatever");
    }

}