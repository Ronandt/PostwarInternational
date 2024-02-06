package com.example.international_a_am

import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.international_a_am.data.Database
import com.example.international_a_am.data.ImageResult
import com.example.international_a_am.data.TicketItem
import com.example.international_a_am.data.WorldSkillsAPI
import com.example.international_a_am.data.screenshot
import com.example.international_a_am.data.showNotification

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun TicketDetailsScreen(navController: NavController, id: String) {
    val view: View = LocalView.current
    val context = LocalContext.current
    var composableBounds by remember {
        mutableStateOf<Rect?>(null)
    }
    var permissions = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission() ) {
        if(!it) {
            Toast.makeText(context, "Note that we can't send you notifications", Toast.LENGTH_SHORT).show()
        }

    }
    var ticket by remember {mutableStateOf<TicketItem?>(null)}
    var tickets = remember {mutableStateListOf<TicketItem>()}
    LaunchedEffect(key1 = Unit ) {
        val db = Database.getDb(context)
        WorldSkillsAPI().getTickets()?.let { tickets.addAll(it) }
        ticket = tickets.filter { t-> t.id == id }.get(0)
        permissions.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        permissions.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        permissions.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
Text("Ticket Details", fontSize = 30.sp)
Spacer(modifier = Modifier.height(20.dp))
Card(modifier = Modifier
    .fillMaxWidth()
    .onGloballyPositioned {
        composableBounds = it.boundsInWindow()
    }) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        AsyncImage(model = ticket?.imageLink, contentDescription = "", modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(), contentScale = ContentScale.Crop)

        Text(text = "Ticket type: ${ticket?.ticketType}")
        Text(text = "Audience's name: ${ticket?.name}")
        Text(text = "Time: ${ticket?.time}")
        Text(text = "Seat: ${ticket?.seat}")
    }


}

    Button(onClick = {



        val result = composableBounds?.let { view.screenshot(it) }

       when(result) {
           is ImageResult.Error -> {
               Toast.makeText(context, "Issue", Toast.LENGTH_SHORT).show()
           }
           is ImageResult.Success -> {
               MediaStore.Images.Media.insertImage(context.getContentResolver(), result.data, ticket?.seat + " Ticket", "Ticket for whatever");

         //      Toast.makeText(context, "Downloaded", Toast.LENGTH_SHORT).show()
           }

           else -> {}
       }
        showNotification(context, "Your ticket has been downloaded", "Ticket: ${ticket?.seat}")







    }) {
        Text(text = "Download")
    }
}
}


