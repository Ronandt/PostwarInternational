package com.example.worldskills

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketCreateScreen(navController: NavController) {
    var dropDownMenuChoices = listOf("Closing Ceremony", "Opening Ceremony")
    val scope = rememberCoroutineScope()
    var expanded by remember {mutableStateOf(false)}
    var selected by remember { mutableStateOf("Closing Ceremony") }
    var name by remember {mutableStateOf("")}
    var uriinfo by remember {mutableStateOf("")}
    val context = LocalContext.current
    var permissionState = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
        if(it != null) {
            uriinfo = it.toString()
            //plezase save me
            context.contentResolver.takePersistableUriPermission(uriinfo.toUri(), Intent.FLAG_GRANT_READ_URI_PERMISSION)


        }
    }
    LaunchedEffect(Unit) {

    }
Column(modifier = Modifier
    .fillMaxSize()
    .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(text = "Ticket create", fontSize = 20.sp)
    Spacer(modifier = Modifier.height(16.dp))
    ExposedDropdownMenuBox(expanded =expanded , onExpandedChange = {expanded = !expanded} ) {
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            dropDownMenuChoices.forEach {
                DropdownMenuItem(text = { Text(text = it)}, onClick = {selected = it })
            }
        }
        OutlinedTextField(value = selected, onValueChange ={}, readOnly = true, modifier = Modifier
            .menuAnchor()
            .fillMaxWidth())

    }
    OutlinedTextField(modifier = Modifier.fillMaxWidth(),value = name, onValueChange = {name = it}, placeholder = { Text(text = "Input your name")})
    Button(onClick = { permissionState.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))}, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Choose one picture")
    }
    Spacer(modifier = Modifier.height(20.dp))
    AsyncImage(model = uriinfo.toUri(), contentDescription = "Andoird background", modifier = Modifier
        .fillMaxWidth()
        .height(200.dp))
    Spacer(modifier = Modifier.weight(1f))
    Button(onClick = {
               scope.launch {
                   RealmDb.getDb().write {
                      var randomAlpha =  listOf("A", "B", "C")
                     var data = TicketData().apply {
                         this.image = uriinfo
                         this.name = name
                         this.type = selected
                         this.time = "904234903223"
                         this.row = "A6 Row7 Column9"
                     }
                       copyToRealm(data)
                   }
                   Toast.makeText(context, "Ticket has been created", Toast.LENGTH_LONG).show()
                   navController.navigate("tickets")
               }

    }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Create")
    }

}
}