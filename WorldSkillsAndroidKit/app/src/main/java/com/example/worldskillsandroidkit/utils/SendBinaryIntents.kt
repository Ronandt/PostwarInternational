package com.example.worldskillsandroidkit.utils

import android.content.Context
import android.content.Intent
import android.os.Environment
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

class SendBinaryIntents(private val context: Context) {

    suspend fun sendBinary(url: String) {
        withContext(Dispatchers.IO) {
            var externalFilePathTmp = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "tmp")

            val url = URL(url)
            url.openStream().copyTo(externalFilePathTmp.outputStream())
            val intent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.type = "image/jpg"
                this.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context, context.applicationContext.packageName + ".provider", externalFilePathTmp))
            }

            context.startActivity(Intent.createChooser(intent, null))

        }




    }
}