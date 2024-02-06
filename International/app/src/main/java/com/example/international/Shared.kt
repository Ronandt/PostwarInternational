package com.example.international

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun LoaderImage(imageLink: String): AsyncImagePainter {
    return rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(imageLink).crossfade(true)
            .placeholder(R.drawable.morecomplexpreloader).build(),
        contentScale = ContentScale.Crop
    )


}