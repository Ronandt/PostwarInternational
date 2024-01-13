package com.example.international.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international.R
import com.example.international.data.DB
import com.example.international.data.Event
import com.example.international.data.Ticket

@Composable
fun TicketDetailsScreen(navController: NavController, id: Int?) {
    var ticket by remember {mutableStateOf<Ticket?>(null)}
    val context = LocalContext.current
    val database = DB.getDatabase(context = context)
    LaunchedEffect(key1 = Unit) {
       ticket = id?.let { database?.ticketDao()?.findTicket(it) }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        Text(text = "Ticket Details", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "", contentScale = ContentScale.Fit, modifier = Modifier
                .fillMaxWidth()
                .height(140.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Ticket Type: ${ticket?.ticketType}")
            Text(text = "Audience's name:  ${ticket?.audienceName}")
            Text(text = "Time:  ${ticket?.time}")
            Text(text = "Seat:  ${ticket?.seat}")


        }

        Spacer(modifier = Modifier.weight(1f))
        Button(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
            Text(text = "Download ")
        }
    }

}