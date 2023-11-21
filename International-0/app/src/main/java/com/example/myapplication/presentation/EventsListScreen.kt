package com.example.myapplication.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.Event
import com.example.myapplication.data.EventsRepository

enum class EventType {
    All,
    Unread,
    Read
}

@Composable
fun EventsListScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = remember {EventsRepository(context)}
    var eventType by remember {mutableStateOf(EventType.All)}
    val events = remember { mutableStateListOf<Event>()}
    LaunchedEffect(Unit) {
        events.addAll(repository.retrieveEvents())
    }
Column(modifier = Modifier
    .fillMaxSize()
    .padding(12.dp)) {
    Text(text = "Events List", fontSize = 28.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
    Spacer(modifier = Modifier.height(14.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally), modifier = Modifier.fillMaxWidth()) {
        EventType.values().forEach {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = it == eventType, onClick = { eventType = it})
                Text(text = it.name)
            }
        }
    }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically)) {
        items(events.size) {
            if(eventType == EventType.All
                || eventType == EventType.Read && events[it].read
                || eventType == EventType.Unread && !events[it].read)
            Row(modifier = Modifier.fillMaxWidth().clickable {
                                                             navController.navigate("eventsDetails/${events[it].id}")
            }, horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)) {

                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "Image" )
                Column {
                    Text(text = events[it].title)
                    Text(text = events[it].text, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    Text(text = if(events[it].read) "Read" else "Unread")
                }

            }

        }
    }

}
}