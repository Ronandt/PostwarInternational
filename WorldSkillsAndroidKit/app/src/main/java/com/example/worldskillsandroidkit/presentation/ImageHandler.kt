package com.example.worldskillsandroidkit.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.worldskillsandroidkit.R

@Composable
fun ImageHandler(imageLink: String): AsyncImagePainter {
    return rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(imageLink).crossfade(true)
            .placeholder(R.drawable.ic_launcher_foreground).build(),
        contentScale = ContentScale.Crop
    )


}