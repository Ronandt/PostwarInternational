package com.example.international.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.international.data.DB
import com.example.international.data.Event
import com.example.international.data.Ticket


@Composable
fun TicketsListScreen(navController: NavController) {
    var ticketC =  remember { mutableStateListOf<Ticket>()}
    var ticketO =  remember { mutableStateListOf<Ticket>()}
    val context = LocalContext.current
    val db = DB .getDatabase(context =context )
    LaunchedEffect(key1 = Unit ) {
        db?.ticketDao()?.findClosingCeremony()?.toList()?.let { ticketC.addAll(it) }
        db?.ticketDao()?.findOpeningCeremony()?.toList()?.let { ticketO.addAll(it) }

    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Tickets List")
        Button(onClick = { navController.navigate("ticketCreate")}, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Create a new ticket")
        }
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            item {
                Text(text = "Opening Ceremony Tickets")
                Spacer(modifier = Modifier.height(20.dp))
            }

            items(ticketC.size) {
                Card(title = ticketC[it].audienceName, position = ticketC[it].seat ) {

                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Closing Ceremony Tickets")
            }
            items(ticketO.size) {
                Card(title = ticketO[it].audienceName, position = ticketO[it].seat) {
                    navController.navigate("ticketDetails/{id}")
                }
            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Card(title: String, position: String, onClick: () -> Unit) {
    androidx.compose.material3.Card(onClick = { /*TODO*/ }, modifier = Modifier
        .fillMaxWidth()
        .size(150.dp)) {
        Text(text = title, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = position, modifier = Modifier.align(Alignment.End))
    }
}