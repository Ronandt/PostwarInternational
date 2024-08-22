package edu.ws2022.aXX.am

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Outline
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import java.io.File
import java.text.SimpleDateFormat

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TicketDetailsScreen(navController: NavController, id: Int?) {
    val context = LocalContext.current
    val database = remember {AppDatabase.getDb(context)}

    var selectedTicket by remember { mutableStateOf<Ticket?>(null)}
    LaunchedEffect(Unit) {
        if (id != null) {
            selectedTicket = database.ticketDao().selectTicket(id)
        }
    }
AppColumn {
    Header(title = "Ticket Details")
   Card(modifier = Modifier.fillMaxWidth()) {
       AsyncImage(
          selectedTicket?.image,
           contentDescription = "Images",
           modifier = Modifier
               .fillMaxWidth()
               .height(140.dp),
           contentScale = ContentScale.Crop
       )

       Text(text = "Tiicket Type:  ${selectedTicket?.ticketType}")
       Text(text = "Audiecnename: ${selectedTicket?.name}")
       Text(text = "Time: ${selectedTicket?.time?.let { it1 -> transformTime(it1) }}")
       Text(text = "Seating: ${selectedTicket?.seat }")
       Spacer(modifier = Modifier.height(200.dp))
   }
    Spacer(modifier = Modifier.height(30.dp))
    Button(modifier = Modifier.fillMaxWidth() , onClick = {
        NotificationService(context).notify("It has been donwloaded")
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "temp")
        context.resources.openRawResource(R.raw.images).copyTo(file.outputStream())
        ContentManager(context).insert(file.inputStream())


    }) {
        Text(text = "Download")
    }
    
}
}

fun transformTime(time: Long): String {
    val sdf = SimpleDateFormat("DD-mm-YYYY HH:MM")
    return sdf.format(time)

}