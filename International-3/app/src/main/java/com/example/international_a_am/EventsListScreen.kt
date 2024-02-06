package com.example.international_a_am

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.international_a_am.data.Database
import com.example.international_a_am.data.EventItem
import com.example.international_a_am.data.WorldSkillsAPI

enum class EventSelection {
    All,
    Unread,
    Read
}
@Composable
fun EventsListScreen(navController: NavController) {
    val context = LocalContext.current
    var events = remember {mutableStateListOf<EventItem>()}
    LaunchedEffect(Unit) {
        WorldSkillsAPI().getEvents()?.let { events.addAll(it.map {
            it.apply {    status =  if (Database.getDb(context)?.eventDao()?.select(it.id.toInt()) == true) "Read" else "Unread" }

        }) }


    }
    var selectedFilter by remember {mutableStateOf(EventSelection.All)}
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "Events List", fontSize = 26.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
            EventSelection.values().forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = it.name == selectedFilter.name, onClick = {
                        selectedFilter = it
                    })
                    Text(text = it.name)

                }
            }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(events.size) {
                if(selectedFilter == EventSelection.All || (selectedFilter.name == events[it].status)) {
                    Row(modifier = Modifier.clickable {
                        navController.navigate("eventDetails/${events[it].id}")
                    }) {
                        val imagePainter = rememberImagePainter(
                            data = events[it].image,
                            builder = {
                                // Specify the placeholder GIF resource (replace with your actual placeholder)
                                placeholder(R.drawable.morecomplexpreloader)
                            }
                        )
                        Image(painter = imagePainter, contentDescription = "image", modifier = Modifier.size(100.dp), contentScale = ContentScale.Crop)
                        Column(verticalArrangement = Arrangement.Center) {
                            Text(text = events[it].title)
                            Text(
                                text = events[it].text  ,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2
                            )

                            events[it].status?.let { it1 -> Text(text = it1) }
                        }
                    }
                }

            }

        }
    }
}