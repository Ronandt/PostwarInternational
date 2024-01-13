package com.example.international_a_am

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international_a_am.data.Database
import com.example.international_a_am.data.Ticket

@Composable
fun TicketDetailsScreen(navController: NavController, id: Int) {
    val context = LocalContext.current
    var ticket by remember {mutableStateOf<Ticket?>(null)}
    LaunchedEffect(key1 = Unit ) {
        val db = Database.getDb(context)
        ticket = db?.ticketDao()?.select(id)

    }
Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
Text("Ticket Details", fontSize = 30.sp)
Spacer(modifier = Modifier.height(20.dp))
Card(modifier = Modifier.fillMaxWidth()) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "background")
        Text(text = "Ticket type: ${ticket?.ticketType}")
        Text(text = "Audience's name: ${ticket?.audienceName}")
        Text(text = "Time: ${ticket?.time}")
        Text(text = "Seat: ${ticket?.seat}")
    }


}
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Download")
    }
}
}