package com.example.myapplication.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data.Db
import com.example.myapplication.data.Ticket

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketsListScreen(navController: NavController) {
    var closingTickets = remember {mutableStateListOf<Ticket>()}
    var openingTickets =  remember {mutableStateListOf<Ticket>()}
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { Db.initialise(context = context)}
    LaunchedEffect(key1 = Unit) {

        openingTickets.addAll(db.ticketDao().retrieveTicketFromType("Opening Ceremony"))
        closingTickets.addAll(db.ticketDao().retrieveTicketFromType("Closing Ceremony"))
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Tickets LIst", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { navController.navigate("ticketCreate") }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Create new ticket")
        }

        Spacer(modifier = Modifier.height(50.dp))
        LazyColumn {
            item {
                Text(text = "Opening Ceremony Tickets")


            }

            items(openingTickets.size){
                Spacer(modifier = Modifier.height(10.dp))
                Card(modifier = Modifier.fillMaxWidth().clickable {
                    navController.navigate("ticketDetails/${openingTickets[it].id}")
                }, onClick = {    navController.navigate("ticketDetails/${openingTickets[it].id}")}) {
                    Text(text = openingTickets[it].audienceName)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = openingTickets[it].seat, modifier = Modifier.align(Alignment.End))
                }

            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Closing Ceremony Tickets")
            }
            items(closingTickets.size){
                Spacer(modifier = Modifier.height(10.dp))
                Card(modifier = Modifier.fillMaxWidth().clickable {
                    navController.navigate("ticketDetails/${closingTickets[it].id}")
                }, onClick = {    navController.navigate("ticketDetails/${closingTickets[it].id}")}) {
                    Text(text = closingTickets[it].audienceName)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = closingTickets[it].seat, modifier = Modifier.align(Alignment.End))
                }
            }


        }




    }
}