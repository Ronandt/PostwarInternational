package com.example.international_a_am

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international_a_am.data.Database
import com.example.international_a_am.data.TicketItem
import com.example.international_a_am.data.WorldSkillsAPI
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun TicketListScreen(navController: NavController) {
    val context = LocalContext.current
    var loading by remember {mutableStateOf(true)}
    var openingCeremony = remember { mutableStateListOf<TicketItem>()}
    var closingCeremony = remember {mutableStateListOf<TicketItem>()}

    LaunchedEffect(Unit) {
        val tickets = WorldSkillsAPI().getTickets()
        var db = Database.getDb(context)
        tickets?.filter { it.ticketType == "Opening Ceremony"}?.let { openingCeremony.addAll(it.sortedBy { it.order }) }

        tickets?.filter {it.ticketType == "Closing Ceremony"}?.let { closingCeremony.addAll(it.sortedBy { it.order })}
        loading = false

    }
    LaunchedEffect(key1 =openingCeremony) {
        println(openingCeremony.toList())
    }
    LaunchedEffect(key1 = closingCeremony ) {
        println(closingCeremony.toList())
    }
Column(modifier = Modifier
    .fillMaxSize()
    .padding(14.dp)
) {
    Text(text = "Tickets List", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
    Spacer(modifier = Modifier.height(20.dp))
    Button(onClick = { navController.navigate("createTicket") }, colors = ButtonDefaults.buttonColors(
        Color.Black), shape = RoundedCornerShape(10.dp)) {
        Icon(imageVector = Icons.Default.Add, contentDescription = " Add", tint = Color.White)
        Text(text = "Create a new ticket ", color = Color.White)
    }

    Spacer(modifier = Modifier.height(30.dp))

    if(loading) {
        CircularProgressIndicator()
    } else {
        Text(text = "Opening Ceremony Tickets", fontWeight = FontWeight.SemiBold, fontSize = 19.sp)
        Spacer(modifier = Modifier.height(20.dp))
        var lazyListState1 = rememberLazyListState()
        if(openingCeremony.size ==0) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.Search, contentDescription ="No items", tint = Color.Gray, modifier = Modifier.size(70.dp))
                Text("No items to display", fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(30.dp))
        } else {
            DragDropList(items = openingCeremony, onMove = {fromIndex, toIndex -> openingCeremony.move(fromIndex, toIndex)}, lazyListState = lazyListState1) {
                    openingCeremony ->
                var deleteFlag by remember {mutableStateOf(false)}
                val swipeableState = rememberSwipeableState(0)
                val anchors = mapOf(0f to 0, 450f to 1)
                if(!deleteFlag) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .swipeable(
                            swipeableState,
                            orientation = Orientation.Horizontal,
                            anchors = anchors
                        )){
                        Card(modifier = Modifier
                            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp), onClick = {navController.navigate("ticketDetails/${openingCeremony.id}")}, colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp)) {
                            Text(text = openingCeremony.name, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                            Text(text = openingCeremony.seat)
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))

                }

                LaunchedEffect(key1 = swipeableState, key2=swipeableState.currentValue) {
                    if(swipeableState.currentValue == 1) {
                        deleteFlag = true
                        WorldSkillsAPI().deleteTicket(id = openingCeremony.id)
                    }

                }
            }
        }
        

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Closing Ceremony Tickets", fontWeight = FontWeight.SemiBold, fontSize = 19.sp)
        Spacer(modifier = Modifier.height(20.dp))
        var lazyListState2 = rememberLazyListState()
        if(closingCeremony.size ==0) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.Search, contentDescription ="No items", tint = Color.Gray, modifier = Modifier.size(70.dp))
                Text("No items to display", fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(30.dp))
        }  else {
            DragDropList(items = closingCeremony, onMove = {fromIndex, toIndex -> closingCeremony.move(fromIndex, toIndex)}, lazyListState = lazyListState2) {
                    openingCeremony ->
                var deleteFlag by remember {mutableStateOf(false)}
                val swipeableState = rememberSwipeableState(0)
                val anchors = mapOf(0f to 0, 450f to 1)
                if(!deleteFlag) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .swipeable(
                            swipeableState,
                            orientation = Orientation.Horizontal,
                            anchors = anchors
                        )){
                        Card(modifier = Modifier
                            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                            .fillMaxWidth()
                            .height(100.dp)
                            , onClick = {navController.navigate("ticketDetails/${openingCeremony.id}")}, colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp)) {
                            Column(modifier = Modifier.padding(6.dp)) {
                                Text(text = openingCeremony.name, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                                Text(text = openingCeremony.seat)
                            }

                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))

                }

                LaunchedEffect(key1 = swipeableState, key2=swipeableState.currentValue) {
                    if(swipeableState.currentValue == 1) {
                        deleteFlag = true
                        WorldSkillsAPI().deleteTicket(id = openingCeremony.id)
                    }

                }
                LaunchedEffect(key1 = lazyListState1) {
                    println(lazyListState1.firstVisibleItemIndex)
                }
                LaunchedEffect(key1 = lazyListState2 ) {
                    println(lazyListState2.firstVisibleItemIndex)
                }

            }
        }

    }




}
}