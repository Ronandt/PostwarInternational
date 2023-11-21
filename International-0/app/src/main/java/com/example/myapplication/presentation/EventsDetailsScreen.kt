package com.example.myapplication.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.Event
import com.example.myapplication.data.EventsRepository

@Composable
fun EventsDetailsScreen(navController: NavController, toIntOrNull: Int?) {
    val context = LocalContext.current
    var event: Event? by remember {mutableStateOf(null)}
    val repository = EventsRepository(context = context)
    var imageDetails by remember {mutableStateOf(false)}

    LaunchedEffect(Unit) {

        if (toIntOrNull != null) {
            event = repository.retrieveEvent(toIntOrNull)
            repository.changeEventStatus(toIntOrNull)
            println(event)

        } else {
            navController.navigate("eventsList")
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Event details", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(30.dp))
        event?.title?.let { Text(text = it) }
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)) {
            repeat(3) {
                
                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "Image1", modifier = Modifier
                    .height(100.dp)
                    .clickable {
                        imageDetails = true
                    })
            }
     

            
        }

        Spacer(modifier = Modifier.height(30.dp))
        event?.text?.let { Text(text =it ) }
    }
    if(imageDetails) {
        Dialog(onDismissRequest = { imageDetails = false}) {
            Column(modifier = Modifier.height(200.dp)) {

                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "Image1", modifier = Modifier.fillMaxSize() )
            }
        }
    }

}