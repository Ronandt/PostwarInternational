package com.example.international.presentation

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international.R
import com.example.international.data.DB
import com.example.international.data.Ticket
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketCreateScreen(navController: NavController) {
    var expanded by remember {mutableStateOf(false  )}
    var fieldValue by remember {mutableStateOf("Opening Ceremony")}
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var name by remember {mutableStateOf("")}
    val database = DB.getDatabase(context)
    var image by remember {mutableStateOf<Uri?>(null)}
    var randomLetter = remember { listOf("A", "B", "C")[listOf(1,2,3).random()]}
    var randomNumber = remember {listOf(1,2,3,4,5,6,7,8,9,10).random()}
    var launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia() ) {
        if(it != null) {
            context.contentResolver.takePersistableUriPermission( it, Intent.FLAG_GRANT_READ_URI_PERMISSION,)
            image = it
        }

    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Ticket Create", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(10.dp))
        ExposedDropdownMenuBox(expanded =expanded , onExpandedChange = {expanded =  !expanded}) {
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(text = { Text(text = "Closing Ceremony") }, onClick = { fieldValue = "Closing Ceremony" })
                DropdownMenuItem(text = {Text(text = "Opening Ceremony")  }, onClick = {fieldValue = "Opening Ceremony"})
            }
            OutlinedTextField(value = fieldValue, onValueChange = {fieldValue = it}, modifier = Modifier.menuAnchor(), readOnly = true, trailingIcon = {
                if(expanded) {
                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Up" )
                } else {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Down" )
                }


            })

        }

        OutlinedTextField(value = name, onValueChange = {name = it}, placeholder =  { Text(text = "Input your name")})
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }) {
            Text(text = "Choose one picture")
        }
        if(image != null) {
            Image(
                BitmapFactory.decodeStream(context.contentResolver.openInputStream(image!!)).asImageBitmap(), contentDescription = "Background", contentScale = ContentScale.Crop, modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp))
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            scope.launch {
                database?.ticketDao()?.insertAll(listOf(Ticket(ticketType = fieldValue, audienceName = name, time = System.currentTimeMillis(), seat = "$randomLetter$randomNumber Row$randomNumber Column$randomNumber", imagePath = image.toString())))
            }
            navController.navigate("tickets")

        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Create")
        }
    }
}