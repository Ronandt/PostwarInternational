package edu.ws2022.aXX.pm

import android.content.Context
import android.content.Intent
import android.os.Environment
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

class SendBinaryStream(private val context: Context) {
    suspend fun send(url: String) {
        val tempFile = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "tmp"
        )
        withContext(Dispatchers.IO) {
            URL(url).openStream().copyTo(tempFile.outputStream())
            var intentM = Intent().apply {
                action = Intent.ACTION_SEND
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context, context.packageName + ".provider", tempFile))



            }
            context.startActivity(Intent.createChooser(intentM, null    ))
        }




    }
}