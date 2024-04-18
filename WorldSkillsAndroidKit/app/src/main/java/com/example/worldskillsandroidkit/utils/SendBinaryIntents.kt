package com.example.worldskillsandroidkit.utils

import android.content.Context
import android.content.Intent
import android.os.Environment
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

class SendBinaryIntents(private val context: Context) {

    suspend fun sendBinary(url: String) {

        withContext(Dispatchers.IO) {
            val tempFile = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "tmp")
            var stream = URL(url)
            stream.openStream().copyTo(tempFile.outputStream())
            var intent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.type = "image/jpg"
                putExtra(Intent.EXTRA_STREAM,FileProvider.getUriForFile(context, context.packageName + ".provider",tempFile) )
            }
            Intent.createChooser(intent, null)

        }




    }
}