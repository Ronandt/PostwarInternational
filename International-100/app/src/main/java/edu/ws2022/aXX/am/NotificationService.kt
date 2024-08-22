package edu.ws2022.aXX.am

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationService(private val context: Context) {
    val notificationMananger = NotificationManagerCompat.from(context)
    val channelID = "CHANNELAPP"
    @RequiresApi(Build.VERSION_CODES.O)
    fun notify(text: String) {

        notificationMananger.createNotificationChannel(NotificationChannel(channelID,  "Channelname" ,NotificationManager.IMPORTANCE_HIGH,))
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationMananger.notify((System.currentTimeMillis()/100).toInt(), NotificationCompat.Builder(context, channelID).setSmallIcon(R.drawable.images).setContentTitle("Ticket has been downloaded!",).setContentText(text).build())

    }
}