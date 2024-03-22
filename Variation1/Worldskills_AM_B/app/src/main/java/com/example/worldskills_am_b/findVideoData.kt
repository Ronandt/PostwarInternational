package com.example.worldskills_am_b

import android.content.Context
import android.media.MediaMetadataRetriever
import androidx.compose.ui.input.key.Key.Companion.U
import androidx.core.net.toUri

fun findData( context: Context,videoUrl: String): String? {
    val retriever = MediaMetadataRetriever()
    retriever.setDataSource( videoUrl, mapOf<String, String>())
    val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
    retriever.release()

    return time

}