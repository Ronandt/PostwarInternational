package com.example.international_a_am

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international_a_am.data.Database
import com.example.international_a_am.data.TicketItem
import com.example.international_a_am.data.WorldSkillsAPI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketListScreen(navController: NavController) {
    val context = LocalContext.current

    var openingCeremony = remember { mutableStateListOf<TicketItem>()}
    var closingCeremony = remember {mutableStateListOf<TicketItem>()}
    LaunchedEffect(Unit) {
        val tickets = WorldSkillsAPI().getTickets()
        var db = Database.getDb(context)
        tickets?.filter { it.ticketType == "Opening Ceremony"}?.let { openingCeremony.addAll(it) }
        tickets?.filter {it.ticketType == "Closing Ceremony"}?.let { closingCeremony.addAll(it) }


    }
Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(text = "Tickets List", fontSize = 24.sp)
    Spacer(modifier = Modifier.height(20.dp))
    Button(onClick = { navController.navigate("createTicket") }) {
        Text(text = "Create a new ticket ")
    }

    Spacer(modifier = Modifier.height(30.dp))

    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Text(text = "Opening ceremony tickets")
            Spacer(modifier = Modifier.height(20.dp))
        }

        items(openingCeremony.size) {
            Card(modifier = Modifier.fillMaxWidth().height(100.dp), onClick = {navController.navigate("ticketDetails/${openingCeremony[it].id}")}) {
                Text(text = openingCeremony[it].name, fontSize = 20.sp)
                Text(text = openingCeremony[it].seat)
            }
        }


        item {   Text(text = "Closing Ceremony tickets")
        }

        items(closingCeremony.size){
            Card(modifier = Modifier.fillMaxWidth().height(100.dp), onClick = {navController.navigate("ticketDetails/${closingCeremony[it].id}")}){
                Text(text = closingCeremony[it].name, fontSize = 20.sp)
                Text(text = closingCeremony[it].seat)
            }
        }

    }


}
}