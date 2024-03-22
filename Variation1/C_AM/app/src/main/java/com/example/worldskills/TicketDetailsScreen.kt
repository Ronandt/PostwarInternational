package com.example.worldskills

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import io.realm.kotlin.ext.query
import org.mongodb.kbson.ObjectId

@Composable
fun TicketDetailsScreen(navController: NavController, it1: String){
    var ticketDetails by remember {mutableStateOf<TicketData?>(null)}
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        println(it1)
        ticketDetails = RealmDb.getDb().query<TicketData>("_id == $0", it1).first().find()
    }
Column(modifier = Modifier
    .fillMaxSize()
    .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(text = "Ticket details", fontSize = 24.sp)

    Spacer(modifier = Modifier.height(20.dp))

    Column(modifier = Modifier.fillMaxWidth()) {


        AsyncImage(model = ticketDetails?.image?.toUri(), contentDescription = "background", modifier = Modifier
            .fillMaxWidth()
            .height(150.dp), contentScale = ContentScale.Crop )
        Column(modifier = Modifier.padding(8.dp )) {
            Text(text = "Ticket type: ${ticketDetails?.type}")
            Text(text = "Audience's name: ${ticketDetails?.name}")
            Text(text = "Time: ${ticketDetails?.time}")
            Text(text = "Seat: ${ticketDetails?.row}")
        }


    }
    Spacer(modifier = Modifier.weight(1f))
    Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Download")
    }

}
}