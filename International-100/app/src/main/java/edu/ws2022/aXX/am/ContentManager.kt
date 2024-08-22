package edu.ws2022.aXX.am

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

class ContentManager(private val context: Context) {
    fun insert(inputStream: InputStream) {
        val item = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues().apply {
            this.put(MediaStore.Images.ImageColumns.TITLE, "Downloaded Ticket ${UUID.randomUUID()}")
        })
        if (item != null) {
            context.contentResolver.openOutputStream(item)?.write(inputStream.readBytes())
        }

    }
}