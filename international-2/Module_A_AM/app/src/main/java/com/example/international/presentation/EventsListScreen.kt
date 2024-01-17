package com.example.international.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international.R
import com.example.international.data.Event
import com.example.international.data.Repository


enum class FilterState {
    Read,
    Unread,
    All
}
@Composable
fun EventsListScreen(navController: NavController){
    var selectedFilter by remember {mutableStateOf(FilterState.All)}
    var events = remember {mutableStateListOf<Event>()}
    val context= LocalContext.current
    val repository = Repository(context)
    LaunchedEffect(Unit) {

        events.addAll(repository.retrieveEvents())
    }
    Column(modifier = Modifier.fillMaxSize()){
        Text(text = "Events List", fontSize = 24.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),  modifier = Modifier.fillMaxWidth()) {
            FilterState.values().forEach {
                Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = selectedFilter == it, onClick = { selectedFilter= it })
                    Text(text = it.name)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(modifier = Modifier.align(Alignment.CenterHorizontally), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(events.size) {
                if(selectedFilter == FilterState.All || (events[it].readStatus == "Read" && selectedFilter == FilterState.Read) ||(events[it].readStatus == "Unread" && selectedFilter == FilterState.Unread )) {
                    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.clickable {
                        navController.navigate("eventDetails/${events[it].id}")
                    }){
                        Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "Image", modifier = Modifier.size(100.dp))
                        Column {
                            Text(text = events[it].title)
                            Text(text = events[it].text, maxLines = 2, overflow = TextOverflow.Ellipsis)
                            Text(text = events[it].readStatus)
                        }
                    }
                }

            }

        }


    }


}
