package edu.ws2022.aXX.am

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TicketsScreen(navController: NavController) {
    val context = LocalContext.current
    val database = remember {AppDatabase.getDb(context)}

    var openingCeremonyTickets = remember { mutableStateListOf<Ticket>()}
    var closingCeremonyTickets = remember { mutableStateListOf<Ticket>()}

    LaunchedEffect(Unit) {
        val dao = database.ticketDao()
        val data = dao.selectAll()
        openingCeremonyTickets.addAll(data.filter { it.ticketType  == "Opening Ceremony" })
        closingCeremonyTickets.addAll(data.filter { it.ticketType == "Closing Ceremony" })

    }
AppColumn {
    Header(title = "Tickets List")
    Spacer(modifier = Modifier.height(20.dp))
    Button(onClick = { navController.navigate("ticketCreate") }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Create a new ticket")

    }
    Spacer(modifier = Modifier.height(20.dp))
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp )) {
        item {
            Text(text = "Opening Ceremony Tickets", fontSize = 20.sp)
        }
        items(openingCeremonyTickets.size) {
            val swipe = rememberSwipeableState(initialValue = 0)
            var hidden by remember {mutableStateOf(false)}
            LaunchedEffect(swipe) {
                if(swipe.currentValue == 1) {
                    database.ticketDao().deleteTicket(openingCeremonyTickets[it])
                    hidden = true
                }
            }
            if(!hidden) {
                Card(modifier = Modifier.fillMaxWidth().offset(swipe.offset.value.dp).swipeable(swipe, anchors = mapOf(0f to 0, 1000f to 1), orientation = Orientation.Horizontal), onClick = {
                    navController.navigate("ticketDetails/${openingCeremonyTickets[it].id   }")
                }, ) {
                    Text(text = "${openingCeremonyTickets[it].name}", fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Text(text = "${openingCeremonyTickets[it].seat}")
                    }


                }
            }

        }

        item {
            Spacer(modifier = Modifier.height(14.dp))
            Text(text = "Closing Ceremony Tickets", fontSize = 20.sp)
        }
        items(closingCeremonyTickets.size) {
            val swipe = rememberSwipeableState(initialValue = 0)
            var hidden by remember {mutableStateOf(false)}

            LaunchedEffect(swipe) {
                if(swipe.currentValue == 1) {
                    database.ticketDao().deleteTicket(closingCeremonyTickets[it])
                    hidden = true
                }
            }
            if(!hidden) {
                Card(modifier = Modifier.fillMaxWidth().offset(swipe.offset.value.dp).swipeable(swipe, anchors = mapOf(0f to 0, 1000f to 1), orientation = Orientation.Horizontal), onClick = {
                    navController.navigate("ticketDetails/${closingCeremonyTickets[it].id   }")
                }) {
                    Text(text = "${closingCeremonyTickets[it].name}", fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Text(text = "${closingCeremonyTickets[it].seat}")
                    }


                }
            }

        }
    }
}
}