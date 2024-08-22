package edu.ws2022.aXX.am

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TicketCreateScreen(navController: NavController) {
    var ceremonyType by remember { mutableStateOf("Closing Ceremony")}
    var name by remember { mutableStateOf("")}
    var expand by remember { mutableStateOf(false   )}
    var imageUri by remember { mutableStateOf<Uri>(Uri.EMPTY)}
    val context = LocalContext.current
    val database = remember { AppDatabase.getDb(context)}
    val rememberImageActvity = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        if(it != null) {
            context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            imageUri  = it
        }
    }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {

    }
AppColumn {
    Header(title = "Ticket Create")
    Spacer(modifier = Modifier.height(20.dp))
    ExposedDropdownMenuBox(expanded = expand, onExpandedChange = {expand = !expand}) {
        DropdownMenu(expanded = expand, onDismissRequest = {expand = false   }) {
            DropdownMenuItem(onClick = {ceremonyType = "Closing Ceremony"}) {
                Text(text = "Closing Ceremony")
            }
            DropdownMenuItem(onClick = { ceremonyType = "Opening Ceremony"}) {
                Text(text = "Opening Ceremony")
            }
        }
        OutlinedTextField(value = ceremonyType, onValueChange = {ceremonyType = it}, modifier = Modifier
            .fillMaxWidth()
            .clickable {

            }, enabled = false)
    }

    OutlinedTextField(value = name, onValueChange = {name = it}, modifier = Modifier.fillMaxWidth(), label = { Text(
        text = "Input your name"
    )})

    Button(onClick = {

                     rememberImageActvity.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

    }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Choose your picture")
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        if(imageUri == Uri.EMPTY) {
           Box(modifier = Modifier
               .height(200.dp)
               .fillMaxWidth()
               .background(Color.Black), contentAlignment = Alignment.Center) {
               Text(text = "Preview image ", color = Color.White)
           }
        } else {
            AsyncImage(imageUri, contentDescription = "Image", contentScale = ContentScale.Crop, modifier= Modifier
                .fillMaxWidth()
                .height(200.dp) )
        }
    
    }

    Spacer(modifier = Modifier.height(100.dp))
    Button(modifier = Modifier.fillMaxWidth(),onClick = {
        scope.launch {
            scope.launch {
                var randomCharacter = listOf("A", "B","C").random()
                val randomNum1 = (0..10).random()
                val randomNum2 = (0..10).random()
                val randomNum3 = (0..10).random()
                database.ticketDao().insertAll(listOf(
                    Ticket(image = imageUri.toString(), name = name, seat = "${randomCharacter}${randomNum1} Row${randomNum2} Column${randomNum3}" , ticketType =  ceremonyType, time = System.currentTimeMillis())
                ))
            }


        }

        navController.navigateUp() }) {
        Text(text = "Create")
    }
    
}
}