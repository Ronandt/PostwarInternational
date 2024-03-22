package com.example.worldskills

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.Coil
import coil.compose.AsyncImage
import io.realm.kotlin.ext.query
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(navController: NavController) {
    var events = remember { mutableStateListOf<Event>()}
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var persistentEvent = remember { mutableStateListOf<EventData>()}
    var loaded by remember {mutableStateOf(false)}
    LaunchedEffect(Unit){
        JsonManager.getEvents(context, scope)?.let { events.addAll(it) }
        persistentEvent.addAll(RealmDb.getDb().query<EventData>().find().toList())
        delay(500)
        loaded = true
    }

    if(loaded) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            var segmentedButtonChoices = listOf("All", "Unread", "Read")
            var selected by remember {mutableStateOf("All")}
            Text(text = "Events list", fontSize = 24.sp )
            SingleChoiceSegmentedButtonRow {
                segmentedButtonChoices.forEachIndexed { index, it ->
                    SegmentedButton(selected = it == selected, onClick = { selected = it }, shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = segmentedButtonChoices.size
                    ) ) {
                        Text(text = it)
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(events.size) {
                    if(selected == "All" || (selected == "Unread" && !persistentEvent[it].read) || (selected == "Read" && persistentEvent[it].read) ) {
                        Card(modifier = Modifier.fillMaxWidth(), onClick = {navController.navigate("eventDetails/" + events[it].id)}    ) {
                            Row {
                                AsyncImage(model = events[it].image, contentDescription = "image", modifier = Modifier.size(100.dp), contentScale = ContentScale.Crop)

                                Column {
                                    Text(text = events[it].title)
                                    Text(text = events[it].text, maxLines = 2, overflow = TextOverflow.Ellipsis)
                                    Text(text = if(persistentEvent[it].read) "Read" else "Unread")

                                }
                            }
                        }
                    }

                }
            }

        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

}