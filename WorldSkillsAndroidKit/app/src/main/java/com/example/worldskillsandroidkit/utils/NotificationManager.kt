package com.example.worldskillsandroidkit.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.worldskillsandroidkit.R

class NotificationManager(private val context: Context) {
    fun showNotification(title: String, message: String) {
        // Create a unique notification channel ID
        val channelId = "Ticket WorldSkills"

        // Create a notification manager
        val notificationManager = NotificationManagerCompat.from(context)

        // Check if the Android version is Oreo or higher and create a notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Ticket Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Build the notification
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background) // Replace with your notification icon
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true) // Close the notification when tapped

        // Show the notification
        val notificationId = (System.currentTimeMillis()/1000).toInt()
        notificationManager.notify(notificationId, notificationBuilder.build())



    }
}


































