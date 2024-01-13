package com.example.international.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.international.R
import com.example.international.data.DB
import com.example.international.data.Event
import com.example.international.data.Repository

@Composable
fun EventDetailsScreen(navController: NavController, id: Int) {
    var dialog by remember {mutableStateOf(false)}
    val context = LocalContext.current
    var event by remember { mutableStateOf<Event?>(null)}
    val repository = Repository(context)
    val database = DB.getDatabase(context)
    LaunchedEffect(Unit) {
        database?.eventDao()?.findEvent(id)?.let { database?.eventDao()?.update(it.apply {
           it.viewCount =  it.viewCount + 1
            it.readStatus = "Read"
        }) }
       event = database?.eventDao()?.findEvent(id)
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Events List", fontSize = 24.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Text(text = "${event?.title}")
        Text(text = "View count: ${event?.viewCount}")
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            repeat(3) {
                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "Rnadom ", modifier = Modifier.clickable {
                    dialog = true
                })
            }
        }
        event?.text?.let { Text(text = it) }
    }

    if(dialog) {
        Dialog(onDismissRequest = { dialog = false  }) {
            Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "", modifier = Modifier.size(200.dp), contentScale = ContentScale.Crop)

        }
    }

}

@Preview
@Composable
fun test() {
    EventDetailsScreen(navController = rememberNavController(), 3)
}