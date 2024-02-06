package com.example.international_a_am

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import aws.smithy.kotlin.runtime.content.ByteStream
import com.example.international_a_am.data.Database
import com.example.international_a_am.data.S3Bucket
import com.example.international_a_am.data.TicketItem
import com.example.international_a_am.data.WorldSkillsAPI
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTicketScreen(navController: NavController) {
    var openDropdown by remember {mutableStateOf(false)}
    var selection by remember { mutableStateOf("Closing Ceremony")}
    var name by remember {mutableStateOf("")}
    var currentImage by remember {mutableStateOf<Uri?>(null)}
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    var db = remember { Database.getDb(context) }
    var launch = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
        if(it !=null) {
            context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            currentImage = it

        }
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Ticket Create")
        Spacer(modifier = Modifier.height(20.dp))
        ExposedDropdownMenuBox(expanded = openDropdown, onExpandedChange = {openDropdown = !openDropdown}) {
            ExposedDropdownMenu(expanded = openDropdown, onDismissRequest = { openDropdown = false }) {
                DropdownMenuItem(text = { Text(text = "Closing Ceremony") }, onClick = { selection = "Closing Ceremony" })
                DropdownMenuItem(text = { Text(text = "Opening Ceremony") }, onClick = { selection = "Opening Ceremony" })
            }
            OutlinedTextField(value = selection, onValueChange = {}, readOnly = true, modifier = Modifier.menuAnchor())
        }
        OutlinedTextField(value =name, onValueChange = {name = it}, label = {Text( "Input your name")})
        Button(onClick = { launch.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))}) {
            Text(text = "Choose one picture")
        }
        if(currentImage != null) {
            Image(BitmapFactory.decodeStream(context.contentResolver.openInputStream(currentImage!!)).asImageBitmap(), contentDescription = "Preview image", modifier = Modifier.size(300.dp    ))
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            scope.launch {
                var number = (1..10).random()
                var randomLetter = listOf("a", "b", "c")[(0 until 3).random()]
                val currentTimeMillis = System.currentTimeMillis()

                val instant = Instant.ofEpochMilli(currentTimeMillis)
                val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                val formattedDateTime = dateTime.format(formatter)
                val imageLink = currentImage?.let {
                    context.contentResolver.openInputStream(
                        it
                    )?.readBytes()?.let { it1 -> ByteStream.fromBytes(it1) }

                }?.let { S3Bucket().upload(byteStream = it) }
                imageLink?.let { TicketItem(UUID.randomUUID().toString(), imageLink = it, ticketType = selection, name = name, time = formattedDateTime, seat = "$randomLetter$number Row$number Column$number", order = 0) }
                    ?.let { WorldSkillsAPI().createTicket(it) }
               // db?.ticketDao()?.insertAll(listOf(Ticket(selection, name, System.currentTimeMillis(), "$number$randomLetter Row$randomLetter Column$randomLetter", currentImage.toString())))

                Toast.makeText(context, "Saved successfully", Toast.LENGTH_LONG).show()
                navController.navigate("ticketsList")
            }

        }) {
            Text(text = "Create")
        }

    }
}