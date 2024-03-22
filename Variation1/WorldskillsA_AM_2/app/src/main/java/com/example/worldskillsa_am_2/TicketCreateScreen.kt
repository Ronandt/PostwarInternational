package com.example.worldskillsa_am_2

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketCreateScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var ticketImage by remember {mutableStateOf("")}
    var activityLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia() ) {
        if(it != null) {
            context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            ticketImage = it.toString()
        }
    }
    var selection by remember {mutableStateOf("Closing Ceremony")}
    var items = listOf("Closing Ceremony", "Opening Ceremony")
    var name by remember {mutableStateOf("")}
    var expanded by remember {mutableStateOf(false)}
Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(text = "Ticket Create", fontSize = 16.sp)
    Spacer(modifier = Modifier.height(20.dp))
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded}) {
        DropdownMenu(expanded = expanded , onDismissRequest = { expanded = false}) {
            items.forEach {
                DropdownMenuItem(text = { Text(text = it) }, onClick = { selection  = it })
            }

        }
        OutlinedTextField(value = selection, onValueChange = {}, readOnly = true, modifier = Modifier
            .fillMaxWidth()
            .menuAnchor())

    }

    OutlinedTextField(value =  name, onValueChange = {name = it}, placeholder = {Text("Input your name")}, modifier = Modifier.fillMaxWidth())
    Button(onClick = { activityLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))}, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Choose one picture")
    }
    if(ticketImage != "") {
        AsyncImage(model = ticketImage.toUri(), contentDescription = "Preview image", contentScale = ContentScale.Crop, modifier = Modifier.size(300.dp))
    }
    Button(onClick = {
               scope.launch {
                   RealmDb.getDb().write {
                       var randomAlpha = listOf("a", "b", "c")[(0..2).random()]
                       var randomRow = (1..10).random()
                       var randomColumn = (1..10).random()
                       var randomSeat = (1..10).random()
                   var data = TicketData() .apply {
                       this.audienceName = name
                       this.ticketImage = ticketImage
                       this.seat = "$randomAlpha$randomSeat Row$randomRow Column$randomColumn"
                       this.timestamp = "13/3/2024 12:35"
                       this.ticketType = selection
                   }
                       this.copyToRealm(data)
                   }
               }
        Toast.makeText(context, "You have created a ticket ", Toast.LENGTH_LONG).show()
        navController.navigate("events")


    }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Create Ticket")
    }

}
}