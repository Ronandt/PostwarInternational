package com.example.worldskillsandroidkit.Practice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.annotation.OptIn
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.media3.common.util.UnstableApi
import com.example.worldskillsandroidkit.R

class Notification(private val context: Context) {
    @OptIn(UnstableApi::class)
    fun notify(message: String) {

        var channelId = "3"
        var notificationManager = NotificationManagerCompat.from(context)
        var notificationChannel  = NotificationChannel(channelId, "DescriptionChannel",
            NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)
        var notification =  NotificationCompat.Builder(context, channelId).setContentTitle("Hello").setSmallIcon(
            R.drawable.ic_launcher_foreground).build()
        if(notificationManager!= null)  {
            notificationManager.notify((System.currentTimeMillis()/100).toInt(), notification)
        }

    }
}