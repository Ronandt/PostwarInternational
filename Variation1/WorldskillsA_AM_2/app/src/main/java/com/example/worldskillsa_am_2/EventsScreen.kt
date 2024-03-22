package com.example.worldskillsa_am_2

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(navController: NavController) {

    var loaded by remember {mutableStateOf(false)}
    LaunchedEffect(Unit) {
        delay(2000L)
        loaded = true
    }
    val context = LocalContext.current
    var eventsData = remember { mutableStateListOf<EventData>()}

    LaunchedEffect(Unit) {

        JsonManager.getEvents(context)?.let { eventsData.addAll(it) }
    }
    if(loaded) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            var selection by remember {mutableStateOf("All")}
            var lists = listOf("All", "Unread", "Read")
            Text(text = "Events list")
            SingleChoiceSegmentedButtonRow {
                lists.forEachIndexed { index: Int, s: String ->
                    SegmentedButton(selected = s == selection, onClick = { selection = s}, shape =SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = lists.size
                    ) ) {
                        Text(text = "$s")
                    }

                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp )) {
                items(eventsData.size) {
                    if(selection == "All" || (selection == "Unread" && !eventsData[it].read) || (selection == "Read" && eventsData[it].read))
                        Card(modifier = Modifier.fillMaxWidth(), onClick = {
                            navController.navigate("eventDetails/${eventsData[it].id}")

                        }) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp   )) {
                                AsyncImage(model = eventsData[it].image.toUri(), contentDescription = "This is the image", modifier = Modifier.size(60.dp), contentScale = ContentScale.Crop)
                                Column {
                                    Text(text = eventsData[it].title)
                                    Text(text = eventsData[it].text, maxLines = 2, overflow = TextOverflow.Ellipsis)
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Spacer(modifier = Modifier.weight(1f))
                                        Text(text = if(eventsData[it].read) "Read" else "Unread")
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