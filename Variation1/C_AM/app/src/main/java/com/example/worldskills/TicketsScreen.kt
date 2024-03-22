package com.example.worldskills

import android.widget.Toast
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TicketsScreen(navController: NavController) {
    var openingCeremony  = remember {mutableStateListOf<TicketData>()}
    var closingCeremony = remember {mutableStateListOf<TicketData>()}
    var gestures = mapOf(0f to 0, 1000f to 1)
    var change by remember {mutableStateOf(false)}
    val context = LocalContext.current
    LaunchedEffect(change) {
        openingCeremony.clear()
        closingCeremony.clear()
       openingCeremony.addAll(RealmDb.getDb().query<TicketData>("type == $0", "Opening Ceremony").find())
        closingCeremony.addAll(RealmDb.getDb().query<TicketData>("type == $0", "Closing Ceremony").find())
    }
Column(modifier = Modifier
    .fillMaxSize()
    .padding(horizontal = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(text = "Tickets list", fontSize = 20.sp)
    Button(onClick = { navController.navigate("ticketCreate") }, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Create a new ticket")
    }



    LazyColumn {
        item {
            Text(text = "Closing Ceremony Tickets")
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(openingCeremony.size) {a->
            val scope = rememberCoroutineScope()
            var swipeState = rememberSwipeableState(initialValue = 0) {
                if(it == 1) {
                    scope.launch {
                        RealmDb.getDb().write {

                            var i =  this.query<TicketData>("_id == $0", openingCeremony[a]._id)
                            delete(i)
                        }
                    }
                    change = !change

                    println(a)
                }
                true
            }

            Box(modifier = Modifier.swipeable(swipeState, anchors = gestures, orientation = Orientation.Horizontal)) {
                Card(modifier = Modifier
                    .offset(x = swipeState.offset.value.dp)
                    .fillMaxWidth(), onClick = {navController.navigate("ticketDetails/${openingCeremony[a]._id}")}) {
                    Text(text = openingCeremony[a].name, fontSize = 16.sp)
                    Text(text = openingCeremony[a].row)

                }
            }

            Spacer(modifier = Modifier.height(4.dp))
        }

        item {

            Text(text = "Opening Ceremony Tickets")
        }

        items(closingCeremony.size) {a->
            val scope = rememberCoroutineScope()
            var swipeState = rememberSwipeableState(initialValue = 0) {
                if(it == 1) {
                    scope.launch {
                        RealmDb.getDb().write {
                            println(a)
                            println(closingCeremony[a]._id)
                            println("How is this possible ")
                           var i =  query<TicketData>("_id == $0", closingCeremony[a]._id).find()
                            delete(i)

                        }
                    }

                    Toast.makeText(context, "Item has been deleted", Toast.LENGTH_LONG ).show()
                    change = !change

                    println(it)
                }
                true
            }
            Box(modifier = Modifier.swipeable(swipeState, anchors = gestures, orientation = Orientation.Horizontal)) {
                Card(
                    modifier = Modifier
                        .offset(x = swipeState.offset.value.dp)
                        .fillMaxWidth(),
                    onClick = {
                        println(closingCeremony[a]._id)
                        navController.navigate("ticketDetails/${closingCeremony[a]._id.toString()}") }) {
                    Text(text = closingCeremony[a].name, fontSize = 16.sp)
                    Text(text = closingCeremony[a].row)

                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }

    }
}
}