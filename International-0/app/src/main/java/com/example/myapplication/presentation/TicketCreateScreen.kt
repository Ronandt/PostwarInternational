package com.example.myapplication.presentation

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data.Db
import com.example.myapplication.data.Ticket
import kotlinx.coroutines.launch
import java.net.URI
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketCreateScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var openDropdown by remember {mutableStateOf(false)}
    val ticketTypes = remember {listOf("Closing Ceremony", "Opening Ceremony")}
    var ticketChoice by remember {mutableStateOf(ticketTypes[0])}
    var name by remember{mutableStateOf("")}
    val rowColumnAllocation = remember { (0..10).random()}
    val letterAllocation = remember{(listOf("A", "B", "C")[(0 until 3).random()])}
    var image by remember {mutableStateOf<Uri?>(null)}
    val visualRequest = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        if(it != null) {
            image = it
            context.contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }


    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Ticket create", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(20.dp))
        ExposedDropdownMenuBox(expanded = openDropdown, onExpandedChange = {openDropdown  = !openDropdown}) {
            ExposedDropdownMenu(expanded = openDropdown, onDismissRequest = { openDropdown = false }) {
                ticketTypes.forEach {
                    DropdownMenuItem(text = { Text(text = it) }, onClick = { ticketChoice = it
                    openDropdown = false
                    })
                }



            }
            OutlinedTextField(value = ticketChoice, onValueChange = {}, readOnly = true,  modifier = Modifier.menuAnchor(), trailingIcon = {
                if(openDropdown) Icon(Icons.Default.KeyboardArrowUp, contentDescription =  " Up") else  Icon(Icons.Default.KeyboardArrowDown, contentDescription =  "Down")
            })


        }

        TextField(value = name, onValueChange = {name  = it}, placeholder = { Text(text = "Input your name")})
        Button(onClick = { visualRequest.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }) {
            Text(text = "Choose one picture")
        }
        Spacer(modifier = Modifier.height(20.dp))
        if(image != null) {
            Image(bitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(
                image!!
            )).asImageBitmap(), contentDescription = "image")
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { if(name == "") {
            Toast.makeText(context, "Name is required", Toast.LENGTH_LONG).show()

        } else {
            scope.launch {
                Db.initialise(context).ticketDao().insert(ticket = Ticket(ticketImage = image.toString(), audienceName = name, ticketType = ticketChoice, seat = "${
                    letterAllocation + rowColumnAllocation

                } Row $rowColumnAllocation Column $rowColumnAllocation" , time = System.currentTimeMillis()))
            }
            navController.navigate("ticketList")
        }

        }) {
            Text(text = "Create")
        }
    }

}