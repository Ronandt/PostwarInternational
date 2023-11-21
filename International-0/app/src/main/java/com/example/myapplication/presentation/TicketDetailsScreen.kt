package com.example.myapplication.presentation

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.Db
import com.example.myapplication.data.Event
import com.example.myapplication.data.EventsRepository
import com.example.myapplication.data.Ticket

@Composable
fun TicketDetailsScreen(navController: NavController, id: Long) {
    var ticket by remember {mutableStateOf<Ticket?>(null)}
    val context = LocalContext.current
LaunchedEffect(key1 = Unit, block = {
    ticket = Db.initialise(context).ticketDao().retrieveTicket(id)
})

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Ticket Details", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Card {
            if(ticket?.ticketImage?.toUri() == null) {

            } else {
                Image(BitmapFactory.decodeStream(ticket?.ticketImage?.toUri()
                    ?.let { context.contentResolver.openInputStream(it) }).asImageBitmap(), contentDescription = "")
            }

            Text(text = "Ticket type: ${ticket?.ticketType}")
            Text(text = "Audience's name: ${ticket?.audienceName}")
            Text(text = "Time: ${ticket?.time}")
            Text(text = "Seat: ${ticket?.seat}")
        }
        Button(onClick = {
            val b =NotificationCompat.Builder(context, "Download")
                .setContentTitle("DOwnload").setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("Thing has been downloaded")
                .setStyle(
                    NotificationCompat.BigTextStyle()
                    .bigText("Downloaded"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

            with(NotificationManagerCompat.from(context)) {
                // notificationId is a unique int for each notification that you must define.
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

                }
                createNotificationChannel(context)?.notify((0..100).random(), b)

            }

        }) {
            Text(text = "Download")
        }
    }

}

private fun createNotificationChannel(context: Context): NotificationManager? {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is not in the Support Library.

    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel("Download", "Download", importance).apply {
        description = "Download"
    }
    // Register the channel with the system.
    val notificationManager: NotificationManager? = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (notificationManager != null) {
        notificationManager.createNotificationChannel(channel)
    }
    return notificationManager
}