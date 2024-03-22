package com.example.worldskillsa_am_2

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TicketsScreen(navController: NavController) {

    var change by remember {mutableStateOf(false)}
    var  openingCeremony     = remember { mutableStateListOf<TicketData>()}
    var closingCeremony = remember {mutableStateListOf<TicketData>()}
    LaunchedEffect(change) {
        val database = RealmDb.getDb()
       openingCeremony.clear()
        closingCeremony.clear()
        openingCeremony.addAll(database.query<TicketData>("ticketType == $0", "Opening Ceremony").find())
        closingCeremony.addAll(database.query<TicketData>("ticketType == $0", "Closing Ceremony").find())
    }
Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
    Text("Tickets list ", fontSize = 16.sp)
    Button(onClick = { navController.navigate("ticketCreate") }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Create a new ticket ")
    }
    Spacer(modifier = Modifier.height(20.dp))

    LazyColumn {
        item {
            Text(text = "Closing Ceremony Tickets")
        }
        items(closingCeremony.size) {a->
            val scope = rememberCoroutineScope()
            val swipeableState = rememberSwipeableState(initialValue = 0) {
                if(it == 1) {
                    println("Hello")
                    scope.launch {
                        val database = RealmDb.getDb()
                        database.write {
                            delete( this.query<TicketData>("id == $0",closingCeremony[a].id))
                        }

                    }
                    change = !change
                }

                true
            }
            Box(modifier = Modifier.swipeable(
                state = swipeableState,
                anchors = mapOf(0f to 0, 1000f to 1),
                orientation = Orientation.Horizontal
            )) {
                Card(modifier = Modifier
                    .offset(x = swipeableState.offset.value.dp)
                    .fillMaxWidth()
                    .height(100.dp), onClick = {navController.navigate("ticketDetails/${closingCeremony[a].id}")}) {
                    Text(text = closingCeremony[a].audienceName)
                    Text(text = closingCeremony[a].seat)
                }
            }

        }
        item {
            Text(text = "Opening Ceremony Tickets")
        }
        items(openingCeremony.size) { a->
            val scope = rememberCoroutineScope()
            val swipeableState = rememberSwipeableState(initialValue = 0) {
                if(it == 1) {
                    println("Hello")
                    scope.launch {
                        val database = RealmDb.getDb()
                        database.write {
                            delete( this.query<TicketData>("id == $0",openingCeremony[a].id))
                        }

                    }
                    change = !change
                }

                true
            }
            Box(modifier = Modifier.swipeable(
                state = swipeableState,
                anchors = mapOf(0f to 0, 1000f to 1),
                orientation = Orientation.Horizontal
            )) {
                Card(modifier = Modifier.offset(x = swipeableState.offset.value.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                    onClick = { navController.navigate("ticketDetails/${openingCeremony[a].id}") }) {
                    Text(text = openingCeremony[a].audienceName)
                    Text(text = openingCeremony[a].seat)
                }
            }
        }
    }

}
}