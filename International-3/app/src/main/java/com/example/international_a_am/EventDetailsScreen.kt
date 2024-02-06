package com.example.international_a_am

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.international_a_am.data.Database
import com.example.international_a_am.data.Event
import com.example.international_a_am.data.EventItem
import com.example.international_a_am.data.WorldSkillsAPI
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun EventDetailsScreen(navController: NavController, id: Int) {
    var data by remember {mutableStateOf<EventItem?>(null)}
    var imageDialog by remember { mutableStateOf<Int?>(null)}
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var event by remember {mutableStateOf<EventItem?>(null)}
    var imageList = remember {mutableStateListOf<String>()}
    LaunchedEffect(Unit) {
        event = WorldSkillsAPI().getEvents()?.filter { it.id.toInt() == id }?.get(0)
       // Database.getDb(context).eventDao().insertAll(listOf(Event()))
        WorldSkillsAPI().updateViewCount(id.toString())
        event?.otherImages?.let { imageList.addAll(it) }
        println(event?.otherImages)
        if(!Database.getDb(context)?.eventDao()?.select(id)!!) {
            Database.getDb(context)?.eventDao()?.insertAll(listOf(Event(id, "", "", "")))
        }

    }

    var pagerState = rememberPagerState(
        initialPage = imageDialog ?: 0,
        initialPageOffsetFraction = 0f
    ) {
        imageList.size
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Back(navController = navController, modifier = Modifier.align(Alignment.Start))
        Text(text = "Event Details")
        Spacer(modifier = Modifier.height(20.dp))
        event?.title?.let { Text(text = it) }
        Text(text = (event?.viewCount?.plus(1)).toString())
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            repeat(imageList.size) {
                AsyncImage(imageList[it], contentDescription = "", modifier = Modifier
                    .size(80.dp)
                    .clickable {
                        imageDialog = it
                        scope.launch {
                            pagerState.scrollToPage(it)
                        }

                    }, contentScale = ContentScale.Crop)
            }


        }
        Spacer(modifier = Modifier.requiredHeight(10.dp))
        event?.text?.let { Text(text = it) }

    }

    if(imageDialog != null)  {
        Dialog(onDismissRequest = { imageDialog = null}) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(imageList[it], contentDescription = null, modifier= Modifier
                        .size(260.dp)
                        .offset(x = 40.dp), contentScale = ContentScale.Crop)
                }

            }

        }
    }

}