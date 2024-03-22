package com.example.worldskillsa_am_2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import androidx.media3.common.util.NotificationUtil
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.realm.kotlin.ext.query
import okhttp3.internal.notify

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun TicketDetailsScreen(navController: NavController, id: String?) {

    val context = LocalContext.current
    var permissions = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
        if(!it) {
            Toast.makeText(context, "We need notifications!", Toast.LENGTH_LONG).show()
        }
    }
    LaunchedEffect(key1 = Unit) {
        permissions.launch(android.Manifest.permission.POST_NOTIFICATIONS)

    }
    var ticketData by remember {mutableStateOf<TicketData?>(null)}
    LaunchedEffect(Unit) {
        val database = RealmDb.getDb()
        ticketData = database.query<TicketData>("id == $0", id).first().find()
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Ticket Details", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(model = ticketData?.ticketImage?.toUri(), contentDescription = "Image")
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Text(text = "Ticket type: ${ticketData?.ticketType}")
                Text(text = "Audience's name: ${ticketData?.audienceName}")
                Text(text = "Time: ${ticketData?.timestamp}")
                Text(text = "Seat: ${ticketData?.seat}")
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            println("?????")
            var notifcationBuilder = NotificationCompat.Builder(context, "Downloaded").setSmallIcon(R.drawable.baseline_home_24).setContentTitle("Your ticket has been downloaded")
                         var notificationManager = NotificationManagerCompat.from(context)
                        var notificationChannel = NotificationChannel( "Downloaded",  "Downloader", NotificationManager.IMPORTANCE_DEFAULT ).apply {
                        this.description = "Download"
                        }

                notificationManager.createNotificationChannel(notificationChannel)


          notificationManager.notify((0..100).random(), notifcationBuilder.build())



        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Download")
        }

    }
}