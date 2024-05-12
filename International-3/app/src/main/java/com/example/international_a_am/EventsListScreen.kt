package com.example.international_a_am

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international_a_am.data.Database
import com.example.international_a_am.data.EventItem
import com.example.international_a_am.data.WorldSkillsAPI
import com.example.international_a_am.ui.theme.LightGray


enum class EventSelection {
    All,
    Unread,
    Read
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
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



        Text(text = "Events", fontSize = 26.sp, modifier = Modifier.align(Alignment.CenterHorizontally), fontWeight = FontWeight.SemiBold)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
            EventSelection.values().forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilterChip(selected =it.name == selectedFilter.name , onClick = { selectedFilter = it }, label = { Text(text = it.name) })



                }
            }
        }
        Spacer(modifier = Modifier.height(14.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.testTag("a")
            .fillMaxHeight()
            .background(
                LightGray
            )) {
            items(events.size) { b->
                AnimatedContent(targetState =selectedFilter == EventSelection.All || (selectedFilter.name == events[b].status) ) {
                    if(it) {
                        Row(modifier = Modifier.testTag(b.toString()).padding(horizontal = 10.dp)) {
                            EventCard(event = events[b], navController = navController)

                        }
                    }
                }


            }

        }
    }
}