package com.example.worldskillsa_am_2

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventDetailsScreen(navController: NavController, id: String?) {
    var eventData by remember {mutableStateOf<EventData?    >(null)}
    LaunchedEffect(Unit) {
        val database = RealmDb.getDb()
        eventData = database.query<EventData>("id == $0", id).find().first()
        database.write {
            var modified = this.query<EventData>("id == $0", id).find().first()
            modified.viewCount += 1
            modified.read = true
        }
    }
    val scope = rememberCoroutineScope()
    var pagerState = rememberPagerState(0, pageCount = { 3 })
    var dialog by remember {mutableStateOf(false)}
    var selected by remember {mutableStateOf(0)}
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Event details", fontSize = 18.sp   )
        Spacer(modifier = Modifier.height(20.dp))
        eventData?.title?.let { Text(text = it) }

        Text(text = (eventData?.viewCount?.plus(1)).toString())

        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            repeat(3) {
                AsyncImage(modifier = Modifier
                    .clickable {
                        scope.launch {
                            dialog = true
                            pagerState.scrollToPage(it)
                        }

                    }
                    .size(100.dp),model  = eventData?.otherImages?.get(it), contentDescription =  "This is the launcher background", contentScale = ContentScale.Crop)
            }
        }

        eventData?.text?.let { Text(text = it) }

        if(dialog) {
            Dialog(onDismissRequest = { dialog = false }) {
                HorizontalPager(state = pagerState) {
                    AsyncImage(model  = eventData?.otherImages?.get(it), contentDescription = null, modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.Crop)
                }
            }

        }

    }

}