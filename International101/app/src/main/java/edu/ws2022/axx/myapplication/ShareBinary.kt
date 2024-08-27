package edu.ws2022.axx.myapplication

import android.content.Context
import android.content.Intent
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

class ShareBinary(private val context: Context) {
   suspend fun shareBinary(url: String) {
       withContext(Dispatchers.IO   ) {
           val temp = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "/tmp")
           val url = URL(url)
           url.openStream().copyTo(temp.outputStream())
           var intent = Intent().apply {
               this.action = Intent.ACTION_SEND
               this.type = "image/jpg"
               this.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context, "${context.packageName}.provider", temp))

           }
           context.startActivity(Intent.createChooser(intent, "Select"))
       }

    }
}