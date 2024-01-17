package com.example.international_a_am

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.international_a_am.data.Database
import com.example.international_a_am.data.Event
import com.example.international_a_am.data.Ticket

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun EventDetailsScreen(navController: NavController, id: Int) {
    var data by remember {mutableStateOf<Ticket?>(null)}
    var imageDialog by remember { mutableStateOf<Int?>(null)}
    var imageList = remember {listOf(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,R.drawable.ic_launcher_background)}
    val context = LocalContext.current
    var event by remember {mutableStateOf<Event?>(null)}
    LaunchedEffect(Unit) {
        data = Database.getDb(context)?.ticketDao()?.select(id)
    }
    var pagerState = rememberPagerState(
        initialPage = imageDialog ?: 0,
        initialPageOffsetFraction = 0f
    ) {
        imageList.size
    }
    LaunchedEffect(Unit) {

        val db  = Database.getDb(context)
        event = db?.eventDao()?.select(id)
        println(id)
        println(event)
        event?.viewCount = event?.viewCount?.plus(1)!!
        event?.status = "Read"
        db?.eventDao()?.update(event!!)

    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Back(navController = navController, modifier = Modifier.align(Alignment.Start))
        Text(text = "Event Details")
        Spacer(modifier = Modifier.height(20.dp))
        event?.title?.let { Text(text = it) }
        Text(text = event?.viewCount.toString())
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            repeat(imageList.size) {
                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "", modifier = Modifier
                    .size(80.dp)
                    .clickable {
                        imageDialog = it
                    })
            }


        }
        Spacer(modifier = Modifier.requiredHeight(10.dp))
        event?.text?.let { Text(text = it) }
    }

    if(imageDialog != null)  {
        Dialog(onDismissRequest = { imageDialog = null}) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                HorizontalPager(state = pagerState) {
                    Image(painterResource(id = imageList[it]), contentDescription = null, modifier=  Modifier.size(200.dp), contentScale = ContentScale.Crop)
                }

            }

        }
    }
}