package edu.ws2022.aXX.am

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage

enum class Selection {
    All,
    Unread,
    Read    
}

@Composable
fun EventScreen(navController: NavController) {
    val context = LocalContext.current
    val database = remember {AppDatabase.getDb(context)}
    var events = remember { mutableStateListOf<Event>()

    }
    LaunchedEffect(key1 = Unit ) {
        events.addAll(database.eventDao().selectAll())
    }
    var currentSelection by remember {mutableStateOf<Selection>(Selection.All)}
    AppColumn {
        Header(title = "Events list")
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(10.dp )) {

            enumValues<Selection>().forEach {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp  ), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = it.toString())
                    RadioButton(selected = currentSelection == it, onClick = { currentSelection = it })
                }

            }

        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp )) {
            items(events.size) {
                if(currentSelection == Selection.All || ( currentSelection == Selection.Read && events[it].status == "Read") ||
                    ( currentSelection == Selection.Unread && events[it].status == "Unread")
                )
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
                    navController.navigate("eventDetails/${events[it].id    }")
                }) {
                    AsyncImage(events[it].image, contentDescription = "Image", modifier = Modifier.size(80.dp   ), contentScale = ContentScale.Crop,  )
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = "${events[it].title}")
                        Text(text = "${events[it].text}", overflow = TextOverflow.Ellipsis, maxLines = 2,)
                        Text(text = "${events[it].status}")
                    }
                }
            }
        }
    }

}