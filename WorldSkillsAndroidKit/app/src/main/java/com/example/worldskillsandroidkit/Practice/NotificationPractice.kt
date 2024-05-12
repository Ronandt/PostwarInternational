package com.example.worldskillsandroidkit.Practice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationPractice(private val context: Context) {
fun showNotification() {
    var notificationManager = NotificationManagerCompat.from(context)
    val channelId = "WorldskillsChannel"
    notificationManager.createNotificationChannel(NotificationChannel(channelId, "WorldskillsChannel", NotificationManager.IMPORTANCE_DEFAULT))
    var notificationBuilder = NotificationCompat.Builder(context, channelId).setContentTitle("f").setContentText("WB").build()
  //  notificationManager.notify(System.currentTimeMillis(), notificationBuilder)
}
}